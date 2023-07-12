package org.example.exceptions;

/**
 * Исключение, выбрасываемое при некорректной формуле
 */
public class IncorrectFormulaException extends FormulaEngineException {
    /**
     * Конструктор класса IncorrectFormulaException
     *
     * @param message Сообщение об ошибке
     */
    public IncorrectFormulaException(String message) {
        super(message);
    }
}
