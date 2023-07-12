package org.example.genericoperations;

import org.assertj.core.data.Offset;
import org.example.exceptions.UnsupportedOpException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class DoubleOperationsTest {

    private GenericOperations<Double> genericOperations;

    @BeforeEach
    void setUp() {
        genericOperations = new DoubleOperations();
    }

    @Test
    void add_WhenValidOperands_ShouldReturnSum() {
        Double result = genericOperations.add(2.0, 3.0);
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    void sub_WhenValidOperands_ShouldReturnDifference() {
        Double result = genericOperations.sub(5.0, 3.0);
        assertThat(result).isEqualTo(2.0);
    }

    @Test
    void div_WhenValidOperands_ShouldReturnQuotient() {
        Double result = genericOperations.div(10.0, 2.0);
        assertThat(result).isEqualTo(5.0);
    }

    @Test
    void mul_WhenValidOperands_ShouldReturnProduct() {
        Double result = genericOperations.mul(2.0, 3.0);
        assertThat(result).isEqualTo(6.0);
    }

    @Test
    void pow_WhenValidOperands_ShouldReturnPower() {
        Double result = genericOperations.pow(2.0, 3.0);
        assertThat(result).isEqualTo(8.0);
    }

    @Test
    void sin_WhenValidOperand_ShouldReturnSine() {
        Double result = genericOperations.sin(Math.PI / 2);
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    void cos_WhenValidOperand_ShouldReturnCosine() {
        Double result = genericOperations.cos(0.0);
        assertThat(result).isEqualTo(1.0);
    }

    @Test
    void tan_WhenValidOperand_ShouldReturnTangent() {
        Double result = genericOperations.tan(Math.PI / 4);
        assertThat(result).isCloseTo(1.0, Offset.offset(1e-5));
    }

    @Test
    void conjunction_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.conjunction(1.0, 0.0))
                .withMessage("Неподдерживаемый тип операции: конъюнкция");
    }

    @Test
    void disjunction_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.disjunction(1.0, 0.0))
                .withMessage("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Test
    void logicalNegation_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.logicalNegation(1.0))
                .withMessage("Неподдерживаемый тип операции: логическое отрицание");
    }

    @Test
    void Negation_WhenValidOperands_ShouldReturnNegation() {
        Double result = genericOperations.negation(2.0);
        assertThat(result).isEqualTo(-2.0);
    }
}
