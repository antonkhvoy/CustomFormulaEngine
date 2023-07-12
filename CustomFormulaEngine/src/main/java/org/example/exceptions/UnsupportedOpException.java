package org.example.exceptions;

/**
 * Исключение, выбрасываемое при вызове неподдерживаемой операции для данного типа операндов
 */
public class UnsupportedOpException extends FormulaEngineException {
    /**
     * Конструктор класса UnsupportedOpException
     *
     * @param message Сообщение об ошибке
     */
    public UnsupportedOpException(String message) {
        super(message);
    }
}
