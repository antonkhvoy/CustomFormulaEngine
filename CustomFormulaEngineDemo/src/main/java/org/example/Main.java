package org.example;

import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        // Создание парсера
        NumberParser<Double> numberParser = Double::parseDouble;
        FormulaParser<Double> parser = new FormulaParser<>(numberParser, Double.class);

        // Регистрация пользовательских функций
        Function<Double> customFunction = ((List<Double> _args) -> _args.stream().reduce(0.0, Double::sum));
        parser.registerFunction("myFunction", customFunction);

        // Регистрация пользовательских операций
        parser.registerBinaryOperation("^", Math::pow);

        // Парсинг формулы
        String formula = "tan(90)";
        var expression = parser.parse(formula);

        // Параметры для вычисления формулы
        Map<String, Double> parameters1 = Map.of("x1", 2.0, "x2", 3.0, "x3", 4.0);
        double result = expression.evaluate(parameters1);
        System.out.println("Result 1: " + result);

        Map<String, Double> parameters2 = Map.of("x1", 3.0, "x2", 4.0, "x3", 10.0);
        double result2 = expression.evaluate(parameters2);
        System.out.println("Result 2: " + result2);
    }
}