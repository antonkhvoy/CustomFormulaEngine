package org.example.expressionelem;

/**
 * Переменная
 */
public class ExpressionVariable implements ExpressionElem {
    private final String value; // Значение переменной

    /**
     * Конструктор класса ExpressionVariable
     *
     * @param value Значение переменной
     */
    public ExpressionVariable(String value) {
        this.value = value;
    }

    /**
     * Возвращает значение переменной
     *
     * @return Значение переменной
     */
    public String getValue() {
        return value;
    }
}
