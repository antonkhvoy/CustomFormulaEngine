package org.example.exceptions;

/**
 * Исключение, выбрасываемое при обнаружении неизвестного аргумента функции
 */
public class UnknownArgumentException extends FormulaEngineException {
    /**
     * Конструктор класса UnknownArgumentException
     *
     * @param message Сообщение об ошибке
     */
    public UnknownArgumentException(String message) {
        super(message);
    }
}
