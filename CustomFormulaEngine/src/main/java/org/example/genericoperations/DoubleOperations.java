package org.example.genericoperations;

import org.example.exceptions.UnsupportedOpException;

public class DoubleOperations implements GenericOperations<Double> {

    @Override
    public Double add(Double op1, Double op2) {
        return op1 + op2;
    }

    @Override
    public Double sub(Double op1, Double op2) {
        return op1 - op2;
    }

    @Override
    public Double div(Double op1, Double op2) {
        return op1 / op2;
    }

    @Override
    public Double mul(Double op1, Double op2) {
        return op1 * op2;
    }

    @Override
    public Double negation(Double op) {
        return -op;
    }

    @Override
    public Double pow(Double op1, Double op2) {
        return Math.pow(op1, op2);
    }

    @Override
    public Double sin(Double op) {
        return Math.sin(op);
    }

    @Override
    public Double cos(Double op) {
        return Math.cos(op);
    }

    @Override
    public Double tan(Double op) {
        return Math.tan(op);
    }

    @Override
    public Double conjunction(Double op1, Double op2) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: конъюнкция");
    }

    @Override
    public Double disjunction(Double op1, Double op2) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Override
    public Double logicalNegation(Double op) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: логическое отрицание");
    }
}
