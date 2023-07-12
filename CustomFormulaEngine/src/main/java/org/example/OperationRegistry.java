package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Реестр операций
 *
 * @param <T> Тип операндов операций, должен быть подтипом Number
 */
public class OperationRegistry<T extends Number> {
    private final Map<String, Operation<T>> unaryOperationMap;

    private final Map<String, Operation<T>> binaryOperationMap;

    /**
     * Конструктор класса OperationRegistry
     * Создает новый объект OperationRegistry и инициализирует пустую карту операций
     */
    public OperationRegistry() {
        unaryOperationMap = new HashMap<>();
        binaryOperationMap = new HashMap<>();
    }

    /**
     * Регистрирует бинарную операцию в реестре
     *
     * @param operator  Строковое представление оператора
     * @param operation Объект операции
     */
    public void registerBinaryOperation(String operator, BinaryOperation<T> operation) {
        binaryOperationMap.put(operator, operation);
    }

    /**
     * Регистрирует унарную операцию в реестре
     *
     * @param operator  Строковое представление оператора
     * @param operation Объект операции
     */
    public void registerUnaryOperation(String operator, UnaryOperation<T> operation) {
        unaryOperationMap.put(operator, operation);
    }

    /**
     * Удаляет унарную операцию из реестра
     *
     * @param operator Строковое представление оператора
     */
    public void unregisterUnaryOperation(String operator) {
        unaryOperationMap.remove(operator);
    }

    /**
     * Удаляет бинарную операцию из реестра
     *
     * @param operator Строковое представление оператора
     */
    public void unregisterBinaryOperation(String operator) {
        binaryOperationMap.remove(operator);
    }

    /**
     * Возвращает множество всех зарегистрированных унарных операций
     *
     * @return Множество строк, содержащих все операторы в реестре
     */
    public Set<String> getAllUnaryOperations() {
        return unaryOperationMap.keySet();
    }

    /**
     * Возвращает множество всех зарегистрированных бинарных операций
     *
     * @return Множество строк, содержащих все операторы в реестре
     */
    public Set<String> getAllBinaryOperations() {
        return binaryOperationMap.keySet();
    }

    /**
     * Возвращает унарную операцию по заданному оператору
     *
     * @param operator Строковое представление оператора
     * @return Объект операции или null, если операция не найдена
     */
    public Operation<T> getUnaryOperation(String operator) {
        return unaryOperationMap.get(operator);
    }

    /**
     * Возвращает бинарную операцию по заданному оператору
     *
     * @param operator Строковое представление оператора
     * @return Объект операции или null, если операция не найдена
     */
    public Operation<T> getBinaryOperation(String operator) {
        return binaryOperationMap.get(operator);
    }
}
