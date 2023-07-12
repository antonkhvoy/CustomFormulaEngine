package org.example.genericoperations;

import org.example.exceptions.UnsupportedOpException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FloatOperationsTest {

    private GenericOperations<Float> genericOperations;

    @BeforeEach
    void setUp() {
        genericOperations = new FloatOperations();
    }

    @Test
    void add_WhenValidOperands_ShouldReturnSum() {
        Float result = genericOperations.add(2.0f, 3.0f);
        assertThat(result).isEqualTo(5.0f);
    }

    @Test
    void sub_WhenValidOperands_ShouldReturnDifference() {
        Float result = genericOperations.sub(5.0f, 3.0f);
        assertThat(result).isEqualTo(2.0f);
    }

    @Test
    void div_WhenValidOperands_ShouldReturnQuotient() {
        Float result = genericOperations.div(10.0f, 2.0f);
        assertThat(result).isEqualTo(5.0f);
    }

    @Test
    void mul_WhenValidOperands_ShouldReturnProduct() {
        Float result = genericOperations.mul(2.0f, 3.0f);
        assertThat(result).isEqualTo(6.0f);
    }

    @Test
    void pow_WhenValidOperands_ShouldReturnPower() {
        Float result = genericOperations.pow(2.0f, 3.0f);
        assertThat(result).isEqualTo(8.0f);
    }

    @Test
    void sin_WhenValidOperand_ShouldReturnSine() {
        Float result = genericOperations.sin((float) Math.PI / 2);
        assertThat(result).isEqualTo(1.0f);
    }

    @Test
    void cos_WhenValidOperand_ShouldReturnCosine() {
        Float result = genericOperations.cos(0.0f);
        assertThat(result).isEqualTo(1.0f);
    }

    @Test
    void tan_WhenValidOperand_ShouldReturnTangent() {
        Float result = genericOperations.tan((float) Math.PI / 4);
        assertThat(result).isEqualTo(1.0f);
    }

    @Test
    void conjunction_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.conjunction(1.0f, 0.0f))
                .withMessage("Неподдерживаемый тип операции: конъюнкция");
    }

    @Test
    void disjunction_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.disjunction(1.0f, 0.0f))
                .withMessage("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Test
    void negation_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.disjunction(1.0f, 0.0f))
                .withMessage("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Test
    void logicalNegation_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.logicalNegation(1.0f))
                .withMessage("Неподдерживаемый тип операции: логическое отрицание");
    }

    @Test
    void Negation_WhenValidOperands_ShouldReturnNegation() {
        Float result = genericOperations.negation(2f);
        assertThat(result).isEqualTo(-2f);
    }
}