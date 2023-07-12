package org.example.genericoperations;

import org.example.exceptions.UnsupportedOpException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class BigDecimalOperationsTest {

    private GenericOperations<BigDecimal> genericOperations;

    @BeforeEach
    void setUp() {
        genericOperations = new BigDecimalOperations();
    }

    @Test
    void add_WhenValidOperands_ShouldReturnSum() {
        BigDecimal op1 = new BigDecimal("2.0");
        BigDecimal op2 = new BigDecimal("3.0");

        BigDecimal result = genericOperations.add(op1, op2);

        assertThat(result).isEqualByComparingTo(new BigDecimal("5.0"));
    }

    @Test
    void sub_WhenValidOperands_ShouldReturnDifference() {
        BigDecimal op1 = new BigDecimal("5.0");
        BigDecimal op2 = new BigDecimal("3.0");

        BigDecimal result = genericOperations.sub(op1, op2);

        assertThat(result).isEqualByComparingTo(new BigDecimal("2.0"));
    }

    @Test
    void div_WhenValidOperands_ShouldReturnQuotient() {
        BigDecimal op1 = new BigDecimal("10.0");
        BigDecimal op2 = new BigDecimal("2.0");

        BigDecimal result = genericOperations.div(op1, op2);

        assertThat(result).isEqualByComparingTo(new BigDecimal("5.0"));
    }

    @Test
    void mul_WhenValidOperands_ShouldReturnProduct() {
        BigDecimal op1 = new BigDecimal("2.0");
        BigDecimal op2 = new BigDecimal("3.0");

        BigDecimal result = genericOperations.mul(op1, op2);

        assertThat(result).isEqualByComparingTo(new BigDecimal("6.0"));
    }

    @Test
    void pow_WhenValidOperands_ShouldReturnPower() {
        BigDecimal op1 = new BigDecimal("2.0");
        BigDecimal op2 = new BigDecimal("3.0");

        BigDecimal result = genericOperations.pow(op1, op2);

        assertThat(result).isEqualByComparingTo(new BigDecimal("8.0"));
    }

    @Test
    void sin_WhenValidOperand_ShouldReturnSine() {
        BigDecimal op = new BigDecimal("0.0");

        BigDecimal result = genericOperations.sin(op);

        assertThat(result).isEqualByComparingTo(new BigDecimal("0.0"));
    }

    @Test
    void cos_WhenValidOperand_ShouldReturnCosine() {
        BigDecimal op = new BigDecimal("0.0");

        BigDecimal result = genericOperations.cos(op);

        assertThat(result).isEqualByComparingTo(new BigDecimal("1.0"));
    }

    @Test
    void tan_WhenValidOperand_ShouldReturnTangent() {
        BigDecimal op = new BigDecimal("0.0");

        BigDecimal result = genericOperations.tan(op);

        assertThat(result).isEqualByComparingTo(new BigDecimal("0.0"));
    }

    @Test
    void conjunction_ShouldThrowException() {
        BigDecimal op1 = new BigDecimal("1.0");
        BigDecimal op2 = new BigDecimal("0.0");

        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.conjunction(op1, op2))
                .withMessage("Неподдерживаемый тип операции: конъюнкция");
    }

    @Test
    void disjunction_ShouldThrowException() {
        BigDecimal op1 = new BigDecimal("1.0");
        BigDecimal op2 = new BigDecimal("0.0");

        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.disjunction(op1, op2))
                .withMessage("Неподдерживаемый тип операции: дизъюнкция");
    }

    @Test
    void logicalNegation_ShouldThrowException() {
        assertThatExceptionOfType(UnsupportedOpException.class)
                .isThrownBy(() -> genericOperations.logicalNegation(new BigDecimal("1.0")))
                .withMessage("Неподдерживаемый тип операции: логическое отрицание");
    }

    @Test
    void Negation_WhenValidOperands_ShouldReturnNegation() {
        BigDecimal result = genericOperations.negation(new BigDecimal("2.0"));
        assertThat(result).isEqualByComparingTo(new BigDecimal("-2.0"));
    }
}