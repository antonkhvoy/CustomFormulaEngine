package org.example;

import org.example.exceptions.IncorrectFormulaException;
import org.example.exceptions.UndefinedOpPrecedenceException;
import org.example.genericoperations.*;
import org.example.expressionelem.*;

import java.math.BigDecimal;
import java.util.*;

/**
 * Парсер формул
 *
 * @param <T> Тип чисел, используемых в формулах, должен быть подтипом Number
 */
public class FormulaParser<T extends Number> {
    private final FunctionRegistry<T> functionRegistry; // Реестр функций
    private final NumberParser<T> numberParser; // Парсер чисел
    private final OperationRegistry<T> operationRegistry; // Реестр операций
    private Map<String, Integer> operatorsPrecedence;  // Порядок операций
    private GenericOperations<T> genericOperations; // Хелпер для выполнения операций

    /**
     * Конструктор класса FormulaParser, регистрирует базовые операторы и функции для начала работы
     *
     * @param numberParser Парсер чисел для типа T
     * @param tClass Класс чисел типа T
     */
    public FormulaParser(NumberParser<T> numberParser, Class<T> tClass) {
        this(numberParser, tClass, true, true, true);
    }

    /**
     * Конструктор класса FormulaParser
     *
     * @param numberParser Парсер чисел для типа T
     * @param tClass Класс чисел типа T
     * @param registerMathOps Регистрировать стандартные математические операции (+-/*)
     * @param registerLogicOps Регистрировать стандартные логические операции (|&)
     * @param registerMathFun Регистрировать стандартные математические функции (tan, sin, cos, pow)
     */
    public FormulaParser(NumberParser<T> numberParser, Class<T> tClass, Boolean registerMathOps, Boolean registerLogicOps, Boolean registerMathFun) {
        this.numberParser = numberParser;
        this.functionRegistry = new FunctionRegistry<>();
        this.operationRegistry = new OperationRegistry<>();
        this.operatorsPrecedence = new HashMap<>();
        createGenericOperations(tClass);
        if (registerMathOps) registerDefaultOperations();
        if (registerLogicOps) registerDefaultLogicOperations();
        if (registerMathFun) registerDefaultMathFunctions();
    }


    /**
     * Разбирает и вычисляет формулу на основе переданных параметров
     *
     * @param formula    Строковое представление формулы
     * @param parameters Параметры для вычисления формулы
     * @return Результат вычисления формулы
     */
    public T parseAndEvaluate(String formula, Map<String, T> parameters) {
        ParsedExpression<T> exp = parse(formula);
        return exp.evaluate(parameters);
    }

    /**
     * Разбирает и вычисляет формулу на основе переданных параметров
     *
     * @param formula    Строковое представление формулы
     * @return Результат вычисления формулы
     */
    public T parseAndEvaluate(String formula) {
        return parseAndEvaluate(formula, new HashMap<>());
    }

    /**
     * Разбирает формулу и возвращает объект ParsedExpression
     *
     * @param formula Строковое представление формулы
     * @return Объект ParsedExpression, содержащий разобранное выражение
     */
    public ParsedExpression<T> parse(String formula) {
        List<ExpressionElem> postfix = infixToPostfix(formula);
        return new ParsedExpression<>(postfix, numberParser);
    }

    /**
     * Преобразует инфиксное выражение в постфиксное выражение
     *
     * @param infix Инфиксное выражение в виде строки
     * @return Список элементов постфиксного выражения
     */
    private List<ExpressionElem> infixToPostfix(String infix) {
        Deque<String> stack = new ArrayDeque<>();
        List<ExpressionElem> postfix = new ArrayList<>();

        Iterator<String> elems = Arrays.stream(infix.split("(?=[^\\w.])|(?<=[^\\w.])")).filter(it -> !it.isBlank()).iterator();

        String prev = "";
        while (elems.hasNext()) {
            String curr = elems.next();
            if (Character.isDigit(curr.charAt(0))) {
                postfix.add(new ExpressionNumber<>(numberParser.parseNumber(curr)));
            } else if (operationRegistry.getAllBinaryOperations().contains(curr) && !(prev.isEmpty() || operationRegistry.getAllBinaryOperations().contains(prev))) {
                while (!stack.isEmpty() && !Objects.equals(stack.peek(), "(") && getPrecedence(stack.peek()) <= getPrecedence(curr)) {
                    postfix.add(new ExpressionOperation<>(operationRegistry.getBinaryOperation(stack.pop())));
                }
                stack.push(curr);
            } else if (operationRegistry.getAllUnaryOperations().contains(curr) && (prev.isEmpty() || operationRegistry.getAllBinaryOperations().contains(prev))) {
                stack.push("U." + curr);
            } else if (curr.equals("(")) {
                stack.push(curr);
            } else if (curr.equals(")")) {
                while (!stack.isEmpty() && !Objects.equals(stack.peek(), "(")) {
                    postfix.add(new ExpressionOperation<>(operationRegistry.getBinaryOperation(stack.pop())));
                }
                if (!stack.isEmpty() && Objects.equals(stack.peek(), "(")) {
                    stack.pop();
                }
            } else if (functionRegistry.getAllFunctions().contains(curr)) {
                if (!Objects.equals(elems.next(), "("))
                    throw new IncorrectFormulaException("Ошибка в формуле");
                List<String> arguments = new ArrayList<>();
                String arg;
                while (!(")".equals(arg = elems.next()))) {
                    if (Objects.equals(arg, ",")) {
                        continue;
                    }
                    arguments.add(arg);
                }
                postfix.add(new ExpressionFunction<>(functionRegistry.getFunction(curr), arguments));
            } else {
                postfix.add(new ExpressionVariable(curr));
            }

            prev = curr;
        }

        while (!stack.isEmpty()) {
            String operand = stack.pop();
            Operation<T> operation;
            if (operand.contains("U."))
                operation = operationRegistry.getUnaryOperation(operand.split("\\.")[1]);
            else
                operation = operationRegistry.getBinaryOperation(operand);
            postfix.add(new ExpressionOperation<>(operation));
        }

        return postfix;
    }

    /**
     * Возвращает приоритет оператора
     *
     * @param operator Строковое представление оператора
     * @return Приоритет оператора
     * @throws UndefinedOpPrecedenceException Если порядок операций не определен
     */
    private int getPrecedence(String operator) {
        Integer precedence = operatorsPrecedence.get(operator);
        if (precedence == null || precedence == -1) {
            throw new UndefinedOpPrecedenceException("Не определен порядок операций");
        }
        return precedence;
    }

    /**
     * Устанавливает порядок операций
     *
     * @param value Соответствие оператора и его приоритета в виде числа
     */
    public void setPrecedence(Map<String, Integer> value) {
        operatorsPrecedence = value;
    }

    public void addPrecedence(Integer precedence, String... operators) {
        for (String op : operators) {
            operatorsPrecedence.put(op, precedence);
        }
    }

    /**
     * Регистрирует функцию в реестре функций
     *
     * @param name     Имя функции
     * @param function Объект функции
     */
    public void registerFunction(String name, Function<T> function) {
        functionRegistry.registerFunction(name, function);
    }

    /**
     * Удаляет функцию из реестра функций
     *
     * @param name Имя функции
     */
    public void unregisterFunction(String name) {
        functionRegistry.unregisterFunction(name);
    }

    /**
     * Регистрирует бинарную операцию в реестре операций
     *
     * @param operator  Строковое представление оператора
     * @param operation Объект операции
     */
    public void registerBinaryOperation(String operator, BinaryOperation<T> operation) {
        operationRegistry.registerBinaryOperation(operator, operation);
    }

    /**
     * Регистрирует унарную операцию в реестре операций
     *
     * @param operator  Строковое представление оператора
     * @param operation Объект операции
     */
    public void registerUnaryOperation(String operator, UnaryOperation<T> operation) {
        operationRegistry.registerUnaryOperation(operator, operation);
    }

    /**
     * Удаляет бинарную операцию из реестра операций
     *
     * @param operator Строковое представление оператора
     */
    public void unregisterBinaryOperation(String operator) {
        operationRegistry.unregisterBinaryOperation(operator);
    }

    /**
     * Удаляет унарную операцию из реестра операций
     *
     * @param operator Строковое представление оператора
     */
    public void unregisterUnaryOperation(String operator) {
        operationRegistry.unregisterUnaryOperation(operator);
    }

    private void registerDefaultOperations() {
        registerBinaryOperation("+", genericOperations::add);
        registerBinaryOperation("-", genericOperations::sub);
        registerBinaryOperation("/", genericOperations::div);
        registerBinaryOperation("*", genericOperations::mul);
        registerUnaryOperation("-", genericOperations::negation);
        addPrecedence(0, "*", "/");
        addPrecedence(1, "+", "-");
    }

    private void registerDefaultLogicOperations() {
        registerBinaryOperation("|", genericOperations::disjunction);
        registerBinaryOperation("&", genericOperations::conjunction);
        registerBinaryOperation("or", genericOperations::disjunction);
        registerBinaryOperation("and", genericOperations::conjunction);
        registerUnaryOperation("!", genericOperations::logicalNegation);
        addPrecedence(3, "&", "and");
        addPrecedence(4, "|", "or");
    }

    private void registerDefaultMathFunctions() {
        registerFunction("pow", ((List<T> it) -> genericOperations.pow(it.get(0), it.get(1))));
        registerFunction("sin", ((List<T> it) -> genericOperations.sin(it.get(0))));
        registerFunction("cos", ((List<T> it) -> genericOperations.cos(it.get(0))));
        registerFunction("tan", ((List<T> it) -> genericOperations.tan(it.get(0))));
    }

    @SuppressWarnings("unchecked")
    private void createGenericOperations(Class<T> tClass) {
        if (tClass == Double.class)
            this.genericOperations = (GenericOperations<T>) new DoubleOperations();
        else if (tClass == Integer.class) {
            this.genericOperations = (GenericOperations<T>) new IntegerOperations();
        } else if (tClass == Float.class) {
            this.genericOperations = (GenericOperations<T>) new FloatOperations();
        } else if (tClass == BigDecimal.class) {
            this.genericOperations = (GenericOperations<T>) new BigDecimalOperations();
        }
    }
}