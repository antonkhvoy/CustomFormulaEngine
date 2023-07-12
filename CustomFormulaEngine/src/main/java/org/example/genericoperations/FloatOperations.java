package org.example.genericoperations;

import org.example.exceptions.UnsupportedOpException;

public class FloatOperations implements GenericOperations<Float> {
    @Override
    public Float add(Float op1, Float op2) {
        return op1 + op2;
    }

    @Override
    public Float sub(Float op1, Float op2) {
        return op1 - op2;
    }

    @Override
    public Float div(Float op1, Float op2) {
        return op1 / op2;
    }

    @Override
    public Float mul(Float op1, Float op2) {
        return op1 * op2;
    }

    @Override
    public Float negation(Float op) {
        return -op;
    }

    @Override
    public Float pow(Float op1, Float op2) {
        return (float) Math.pow(op1, op2);
    }

    @Override
    public Float sin(Float op) {
        return (float) Math.sin(op);
    }

    @Override
    public Float cos(Float op) {
        return (float) Math.cos(op);
    }

    @Override
    public Float tan(Float op) {
        return (float) Math.tan(op);
    }

    @Override
    public Float conjunction(Float op1, Float op2) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: конъюнкция");
    }

    @Override
    public Float disjunction(Float op1, Float op2) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Override
    public Float logicalNegation(Float op) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: логическое отрицание");
    }
}
