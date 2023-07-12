package org.example;

import org.example.exceptions.IncorrectFormulaException;
import org.example.exceptions.UndefinedOpPrecedenceException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class FormulaParserTests {

    @ParameterizedTest
    @CsvSource({
            "2 * 3 + 4, 10",
            "14 / (3 + 4), 2",
            "2 - 1 * 3 + 4, 3"
    })
    void parseAndEvaluate_SimpleMathOperations(String formula, Integer expectedResult) {
        FormulaParser<Integer> formulaParser = new FormulaParser<>(Integer::parseInt, Integer.class);

        Integer result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "-(1 + 2), -3",
            "3 + -2, 1",
    })
    void parseAndEvaluate_UnaryOperations(String formula, Integer expectedResult) {
        FormulaParser<Integer> formulaParser = new FormulaParser<>(Integer::parseInt, Integer.class);

        Integer result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2.5 + 1.5, 4.0",
            "1.1 - 2.2, -1.1",
            "2.0 * 5.5, 11.0",
            "10.0 / 5.0, 2.0",
    })
    void parseAndEvaluate_OperationWithDouble(String formula, Double expectedResult) {
        FormulaParser<Double> formulaParser = new FormulaParser<>(Double::parseDouble, Double.class);

        Double result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2.5 + 1.5, 4.0",
            "1.1 - 2.2, -1.1",
            "2.0 * 5.5, 11.0",
            "10.0 / 5.0, 2.0",
    })
    void parseAndEvaluate_OperationWithFloat(String formula, Float expectedResult) {
        FormulaParser<Float> formulaParser = new FormulaParser<>(Float::parseFloat, Float.class);

        Float result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2.5 + 1.5, 4.0",
            "1.1 - 2.2, -1.1",
            "2.0 * 5.5, 11.0",
            "10.0 / 5.0, 2.0",
    })
    void parseAndEvaluate_OperationWithBigDecimal(String formula, BigDecimal expectedResult) {
        FormulaParser<BigDecimal> formulaParser = new FormulaParser<>(BigDecimal::new, BigDecimal.class);

        BigDecimal result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualByComparingTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "!1, 0",
            "!0, 1",
            "1 | 0, 1",
            "1 or 1, 1",
            "1 and 0, 0",
            "1 & 1, 1",
            "1 | 0 & 1 | 0, 1",
    })
    void parseAndEvaluate_SimpleLogicalOperations(String formula, Integer expectedResult) {
        FormulaParser<Integer> formulaParser = new FormulaParser<>(Integer::parseInt, Integer.class);

        Integer result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "sin(0); 0.0",
            "cos(0); 1.0",
            "tan(0); 0.0",
            "pow(2, 4); 16.0",
    }, delimiter = ';')
    void parseAndEvaluate_SimpleMathFunctions(String formula, Double expectedResult) {
        FormulaParser<Double> formulaParser = new FormulaParser<>(Double::parseDouble, Double.class);

        Double result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "(1 + 2 ( 1 )",
            "1 * * 2",
            "1 +",
    })
    void parseAndEvaluate_WhenInvalidFormula_ShouldThrowException(String formula) {
        FormulaParser<Integer> formulaParser = new FormulaParser<>(Integer::parseInt, Integer.class);

        assertThatExceptionOfType(IncorrectFormulaException.class)
                .isThrownBy(() -> formulaParser.parseAndEvaluate(formula))
                .withMessage("Ошибка в формуле");
    }

    @Test
    void parseAndEvaluate_WhenDoubleInIntegerContext_ShouldThrowException() {
        FormulaParser<Integer> formulaParser = new FormulaParser<>(Integer::parseInt, Integer.class);

        assertThatExceptionOfType(NumberFormatException.class)
                .isThrownBy(() -> formulaParser.parseAndEvaluate("12.0 + 1"))
                .withMessage("For input string: \"12.0\"");
    }

    @ParameterizedTest
    @CsvSource({
            "2 * 3 + 4, 14",
            "2 - 1 * 3 + 4, 7"
    })
    void parseAndEvaluate_ChangeOperationPrecedence(String formula, Integer expectedResult) {
        FormulaParser<Integer> formulaParser = new FormulaParser<>(Integer::parseInt, Integer.class);
        formulaParser.setPrecedence(Map.of("+", 0, "-", 0, "*", 1, "/", 1));

        Integer result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @ParameterizedTest
    @CsvSource({
            "2 ^ 3, 8",
            "~2, 8"
    })
    void parseAndEvaluate_DefineUserOperations(String formula, Double expectedResult) {
        FormulaParser<Double> formulaParser = new FormulaParser<>(Double::parseDouble, Double.class);
        formulaParser.registerBinaryOperation("^", Math::pow);
        formulaParser.registerUnaryOperation("~", ((Double op) -> 10 - op));
        formulaParser.setPrecedence(Map.of("pow", 0));

        Double result = formulaParser.parseAndEvaluate(formula);

        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void parseAndEvaluate_DefineUserFunctions() {
        FormulaParser<Double> formulaParser = new FormulaParser<>(Double::parseDouble, Double.class);
        formulaParser.registerFunction("mySuperFun", ((List<Double> _args) -> _args.stream().reduce(0.0, Double::sum)));

        Double result = formulaParser.parseAndEvaluate("mySuperFun(10, 11, 2)");

        assertThat(result).isEqualTo(23);
    }

    @ParameterizedTest
    @CsvSource({
            "12, 11, 133",
            "5, 2, 11",
            "1, 1, 2"
    })
    void parseAndEvaluate_UseVariables(Double x, Double y, Double rez) {
        FormulaParser<Double> formulaParser = new FormulaParser<>(Double::parseDouble, Double.class);

        var params = Map.of("x", x, "y", y);
        Double result = formulaParser.parseAndEvaluate("x * y + 1", params);

        assertThat(result).isEqualTo(rez);
    }

    @Test
    void setPrecedence_WhenInvalidOperator_ShouldThrowException() {
        FormulaParser<Double> formulaParser = new FormulaParser<>(Double::parseDouble, Double.class);
        formulaParser.setPrecedence(Map.of());

        assertThatExceptionOfType(UndefinedOpPrecedenceException.class)
                .isThrownBy(() -> formulaParser.parseAndEvaluate("1 + 1 * 1"))
                .withMessage("Не определен порядок операций");
    }
}

