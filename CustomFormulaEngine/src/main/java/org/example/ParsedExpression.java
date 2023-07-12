package org.example;

import org.example.exceptions.FormulaEngineException;
import org.example.exceptions.IncorrectFormulaException;
import org.example.exceptions.UnknownArgumentException;
import org.example.exceptions.UnsupportedOpException;
import org.example.expressionelem.*;

import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Объект, содержащий выражение в постфиксной нотации
 * Предоставляет методы для вычисления значения выражения на основе переданных параметров
 *
 * @param <T> тип чисел, используемых в выражении
 */
public class ParsedExpression<T extends Number> {
    private final List<ExpressionElem> postfix; // Список элементов выражения в постфиксной нотации

    private final NumberParser<T> numberParser; // Парсер чисел для типа T

    /**
     * Конструктор класса ParsedExpression
     *
     * @param postfix       список элементов выражения в постфиксной нотации
     * @param numberParser  парсер чисел для типа T
     */
    ParsedExpression(List<ExpressionElem> postfix, NumberParser<T> numberParser) {
        this.postfix = postfix;
        this.numberParser = numberParser;
    }

    /**
     * Вычисляет значение выражения на основе переданных параметров
     *
     * @param parameters  карта параметров с их значениями
     * @return значение выражения
     * @throws IncorrectFormulaException если произошла ошибка в выражении
     */
    @SuppressWarnings("unchecked")
    T evaluate(Map<String, T> parameters) {
        Stack<T> stack = new Stack<>(); // Стек для выполнения операций и хранения результатов

        try {
            for (ExpressionElem token : postfix) {
                if (token instanceof ExpressionNumber<?>) {
                    // Если элемент является числом, помещаем его значение в стек
                    stack.push(((ExpressionNumber<T>) token).getValue());
                } else if (token instanceof ExpressionOperation<?>) {
                    Operation<T> operation = ((ExpressionOperation<T>) token).getValue();
                    T result;
                    if (operation instanceof BinaryOperation<T>) {
                        // Если элемент является бинарной операцией, выполняем операцию над двумя верхними значениями в стеке
                        T operand2 = stack.pop();
                        T operand1 = stack.pop();
                        result = ((BinaryOperation<T>) operation).performOperation(operand1, operand2);
                    } else if (operation instanceof UnaryOperation<T>) {
                        // Если элемент является унарной операцией, выполняем операцию над верхним значением в стеке
                        T operand = stack.pop();
                        result = ((UnaryOperation<T>) operation).performOperation(operand);
                    } else
                        throw new IncorrectFormulaException("Ошибка в формуле");
                    stack.push(result);
                } else if (token instanceof ExpressionFunction<?>) {
                    // Если элемент является функцией, вызываем функцию, передавая ей аргументы
                    Function<T> fun = ((ExpressionFunction<T>) token).getFunction();
                    List<String> args = ((ExpressionFunction<?>) token).getArguments();
                    List<T> calculatedArgs = args.stream().map(it -> calculateArg(it, parameters)).toList();
                    stack.push(fun.invoke(calculatedArgs));
                } else if (token instanceof ExpressionVariable) {
                    // Если элемент является переменной, получаем ее значение из параметров и помещаем в стек
                    T value = parameters.get(((ExpressionVariable) token).getValue());
                    stack.push(value);
                }
            }
        }
        catch (FormulaEngineException e) {
            throw e;
        }
        catch (Exception e) {
            throw new IncorrectFormulaException("Ошибка в формуле");
        };
        return stack.pop(); // Возвращаем результат из стека
    }

    /**
     * Вычисляет значение аргумента функции на основе переданных параметров
     *
     * @param arg         аргумент функции
     * @param parameters  карта параметров с их значениями
     * @return значение аргумента
     * @throws UnknownArgumentException если аргумент функции не распознан
     */
    private T calculateArg(String arg, Map<String, T> parameters) {
        if (Character.isDigit(arg.charAt(0))) {
            // Если аргумент является числом, парсим его и возвращаем
            return numberParser.parseNumber(arg);
        }
        if (parameters.containsKey(arg)) {
            // Если аргумент является именем переменной, получаем ее значение из параметров и возвращаем
            return parameters.get(arg);
        }
        throw new UnknownArgumentException("Аргумент функции не распознан");
    }
}
