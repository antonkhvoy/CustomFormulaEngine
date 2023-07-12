package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Реестр функций
 *
 * @param <T> Тип аргументов и результата функций, должен быть подтипом Number
 */
public class FunctionRegistry<T extends Number> {
    private final Map<String, Function<T>> functionMap; // Карта функций

    /**
     * Конструктор класса FunctionRegistry
     * Создает новый объект FunctionRegistry и инициализирует пустую карту функций
     */
    public FunctionRegistry() {
        functionMap = new HashMap<>();
    }

    /**
     * Регистрирует функцию в реестре
     *
     * @param name     Имя функции
     * @param function Объект функции
     */
    public void registerFunction(String name, Function<T> function) {
        functionMap.put(name, function);
    }

    /**
     * Удаляет функцию из реестра
     *
     * @param name Имя функции
     */
    public void unregisterFunction(String name) {
        functionMap.remove(name);
    }

    /**
     * Возвращает множество всех зарегистрированных функций
     *
     * @return Множество строк, содержащих имена всех функций в реестре
     */
    public Set<String> getAllFunctions() {
        return functionMap.keySet();
    }

    /**
     * Возвращает функцию по заданному имени
     *
     * @param name Имя функции
     * @return Объект функции или null, если функция не найдена
     */
    public Function<T> getFunction(String name) {
        return functionMap.get(name);
    }
}
