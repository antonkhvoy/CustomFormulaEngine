package org.example.genericoperations;

public class IntegerOperations implements GenericOperations<Integer> {
    @Override
    public Integer add(Integer op1, Integer op2) {
        return op1 + op2;
    }

    @Override
    public Integer sub(Integer op1, Integer op2) {
        return op1 - op2;
    }

    @Override
    public Integer div(Integer op1, Integer op2) {
        return op1 / op2;
    }

    @Override
    public Integer mul(Integer op1, Integer op2) {
        return op1 * op2;
    }

    @Override
    public Integer negation(Integer op) {
        return -op;
    }

    @Override
    public Integer pow(Integer op1, Integer op2) {
        return (int) Math.pow(op1, op2);
    }

    @Override
    public Integer sin(Integer op) {
        return (int) Math.sin(op);
    }

    @Override
    public Integer cos(Integer op) {
        return (int) Math.cos(op);
    }

    @Override
    public Integer tan(Integer op) {
        return (int) Math.tan(op);
    }

    @Override
    public Integer conjunction(Integer op1, Integer op2) {
        return op1 & op2;
    }

    @Override
    public Integer disjunction(Integer op1, Integer op2) {
        return op1 | op2;
    }

    @Override
    public Integer logicalNegation(Integer op) {
        return (op == 1) ? 0 : 1;
    }
}
