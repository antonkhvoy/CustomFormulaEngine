package org.example;

/**
 * Интерфейс парсера чисел
 *
 * @param <T> Тип чисел, должен быть подтипом Number
 */
public interface NumberParser<T extends Number> {
    /**
     * Преобразует строку в числовое значение
     *
     * @param numberString Строковое представление числа
     * @return Числовое значение
     */
    T parseNumber(String numberString);
}
