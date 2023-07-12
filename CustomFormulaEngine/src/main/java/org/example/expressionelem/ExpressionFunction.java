package org.example.expressionelem;

import org.example.Function;

import java.util.List;

/**
 * Функция
 *
 * @param <T> Тип аргументов и результата функции, должен быть подтипом Number
 */
public class ExpressionFunction<T extends Number> implements ExpressionElem {
    private final Function<T> function; // Функция
    private final List<String> arguments; // Аргументы функции

    /**
     * Конструктор класса ExpressionFunction
     *
     * @param function  Функция
     * @param arguments Аргументы функции
     */
    public ExpressionFunction(Function<T> function, List<String> arguments) {
        this.function = function;
        this.arguments = arguments;
    }

    /**
     * Возвращает функцию
     *
     * @return Функция
     */
    public Function<T> getFunction() {
        return function;
    }

    /**
     * Возвращает список аргументов функции
     *
     * @return Список аргументов функции
     */
    public List<String> getArguments() {
        return arguments;
    }
}
