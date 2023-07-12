package org.example.exceptions;

/**
 * Общее исключение
 */
public class FormulaEngineException extends RuntimeException {
    /**
     * Конструктор класса FormulaEngineException
     *
     * @param message Сообщение об ошибке
     */
    public FormulaEngineException(String message) {
        super(message);
    }
}
