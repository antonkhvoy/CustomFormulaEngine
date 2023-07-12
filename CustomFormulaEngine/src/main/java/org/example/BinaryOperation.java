package org.example;

public interface BinaryOperation<T extends Number> extends Operation<T> {

    /**
     * Выполняет операцию над двумя операндами
     *
     * @param operand1 Первый операнд
     * @param operand2 Второй операнд
     * @return Результат операции
     */
    T performOperation(T operand1, T operand2);
}
