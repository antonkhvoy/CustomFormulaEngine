package org.example.expressionelem;

/**
 * Число
 *
 * @param <T> Тип числа, должен быть подтипом Number
 */
public class ExpressionNumber<T extends Number> implements ExpressionElem {
    private final T value; // Значение числа

    /**
     * Конструктор класса ExpressionNumber
     *
     * @param value Значение числа
     */
    public ExpressionNumber(T value) {
        this.value = value;
    }

    /**
     * Возвращает значение числа
     *
     * @return Значение числа
     */
    public T getValue() {
        return value;
    }
}
