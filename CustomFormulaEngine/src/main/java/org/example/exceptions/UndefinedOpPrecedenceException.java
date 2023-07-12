package org.example.exceptions;

/**
 * Исключение, выбрасываемое при отсутствии определения порядка операций
 */
public class UndefinedOpPrecedenceException extends FormulaEngineException {
    /**
     * Конструктор класса UndefinedOpPrecedenceException
     *
     * @param message Сообщение об ошибке
     */
    public UndefinedOpPrecedenceException(String message) {
        super(message);
    }
}
