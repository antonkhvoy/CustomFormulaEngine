package org.example;

public interface UnaryOperation<T extends Number> extends Operation<T> {

    /**
     * Выполняет операцию над одним операндом
     *
     * @param operand Первый операнд
     * @return Результат операции
     */
    T performOperation(T operand);
}