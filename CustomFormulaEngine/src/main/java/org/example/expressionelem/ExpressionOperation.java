package org.example.expressionelem;

import org.example.Operation;

/**
 * Операция
 *
 * @param <T> Тип операндов и результата операции, должен быть подтипом Number
 */
public class ExpressionOperation<T extends Number> implements ExpressionElem {
    private final Operation<T> value; // Значение операции

    /**
     * Конструктор класса ExpressionOperation
     *
     * @param value Значение операции
     */
    public ExpressionOperation(Operation<T> value) {
        this.value = value;
    }

    /**
     * Возвращает значение операции
     *
     * @return Значение операции
     */
    public Operation<T> getValue() {
        return value;
    }
}
