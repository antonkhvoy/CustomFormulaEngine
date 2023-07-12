package org.example.genericoperations;

import org.example.exceptions.UnsupportedOpException;

import java.math.BigDecimal;

public class BigDecimalOperations implements GenericOperations<BigDecimal> {

    @Override
    public BigDecimal add(BigDecimal op1, BigDecimal op2) {
        return op1.add(op2);
    }

    @Override
    public BigDecimal sub(BigDecimal op1, BigDecimal op2) {
        return op1.subtract(op2);
    }

    @Override
    public BigDecimal div(BigDecimal op1, BigDecimal op2) {
        return op1.divide(op2);
    }

    @Override
    public BigDecimal mul(BigDecimal op1, BigDecimal op2) {
        return op1.multiply(op2);
    }

    @Override
    public BigDecimal negation(BigDecimal op) {
        return op.negate();
    }

    @Override
    public BigDecimal pow(BigDecimal op1, BigDecimal op2) {
        return BigDecimal.valueOf(Math.pow(op1.doubleValue(), op2.doubleValue()));
    }

    @Override
    public BigDecimal sin(BigDecimal op) {
        return BigDecimal.valueOf(Math.sin(op.doubleValue()));
    }

    @Override
    public BigDecimal cos(BigDecimal op) {
        return BigDecimal.valueOf(Math.cos(op.doubleValue()));
    }

    @Override
    public BigDecimal tan(BigDecimal op) {
        return BigDecimal.valueOf(Math.tan(op.doubleValue()));
    }

    @Override
    public BigDecimal conjunction(BigDecimal op1, BigDecimal op2) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: конъюнкция");
    }

    @Override
    public BigDecimal disjunction(BigDecimal op1, BigDecimal op2) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Override
    public BigDecimal logicalNegation(BigDecimal op) {
        throw new UnsupportedOpException("Неподдерживаемый тип операции: логическое отрицание");
    }
}
