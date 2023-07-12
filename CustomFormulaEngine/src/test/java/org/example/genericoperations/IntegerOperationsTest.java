package org.example.genericoperations;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

class IntegerOperationsTest {

    private GenericOperations<Integer> genericOperations;

    @BeforeEach
    void setUp() {
        genericOperations = new IntegerOperations();
    }

    @Test
    void add_WhenValidOperands_ShouldReturnSum() {
        Integer result = genericOperations.add(2, 3);
        assertThat(result).isEqualTo(5);
    }

    @Test
    void sub_WhenValidOperands_ShouldReturnDifference() {
        Integer result = genericOperations.sub(5, 3);
        assertThat(result).isEqualTo(2);
    }

    @Test
    void div_WhenValidOperands_ShouldReturnQuotient() {
        Integer result = genericOperations.div(10, 2);
        assertThat(result).isEqualTo(5);
    }

    @Test
    void mul_WhenValidOperands_ShouldReturnProduct() {
        Integer result = genericOperations.mul(2, 3);
        assertThat(result).isEqualTo(6);
    }

    @Test
    void pow_WhenValidOperands_ShouldReturnPower() {
        Integer result = genericOperations.pow(2, 3);
        assertThat(result).isEqualTo(8);
    }

    @Test
    void sin_WhenValidOperand_ShouldReturnSine() {
        Integer result = genericOperations.sin(0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void cos_WhenValidOperand_ShouldReturnCosine() {
        Integer result = genericOperations.cos(0);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void tan_WhenValidOperand_ShouldReturnTangent() {
        Integer result = genericOperations.tan(0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void conjunction_WhenValidOperands_ShouldReturnConjunction() {
        Integer result = genericOperations.conjunction(1, 0);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void disjunction_WhenValidOperands_ShouldReturnDisjunction() {
        Integer result = genericOperations.disjunction(1, 0);
        assertThat(result).isEqualTo(1);
    }

    @Test
    void LogicalNegation_WhenValidOperands_ShouldReturnLogicalNegation() {
        Integer result = genericOperations.logicalNegation(1);
        assertThat(result).isEqualTo(0);
    }

    @Test
    void Negation_WhenValidOperands_ShouldReturnNegation() {
        Integer result = genericOperations.negation(2);
        assertThat(result).isEqualTo(-2);
    }
}