package org.example;

import java.util.List;

/**
 * Интерфейс функции
 *
 * @param <T> Тип аргументов и результата функции, должен быть подтипом Number
 */
public interface Function<T extends Number> {
    /**
     * Выполняет функцию с заданными аргументами
     *
     * @param arguments Список аргументов функции
     * @return Результат выполнения функции
     */
    T invoke(List<T> arguments);
}
