package io.teamdev.javaclasses.calculatortests;

import io.teamdev.javaclasses.calculator.MathExpressionCalculator;
import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.math.WrongCountOfArgumentsException;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Test for API MathCalculator.
 */

class MathExpressionCalculatorTest {

    private final MathExpressionCalculator mathCalculator = new MathExpressionCalculator();

    @ParameterizedTest
    @CsvSource({"123,123.0", "0.0,0.0", "67.89,67.89", "-98.098,-98.098"})
    void testCorrectEvaluationOfNumber(String input, double expected)
            throws IncorrectFormatOfExpressionException {

        ValueHolder resultHolder = mathCalculator.evaluate(input).get();

        assertWithMessage("Evaluation of number is failed.")
                .that(resultHolder.value())
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"7 + 2.25 / 2.25 + 3, 11.0", "1 + 4/2.5 * 5-2.5 + 1 * (-2), 4.5", "5.01 - 7 * 8 / 2 - 11.01, -34.0"})
    void testCorrectEvaluationOfExpression(String input, double expected)
            throws IncorrectFormatOfExpressionException {

        ValueHolder resultHolder = mathCalculator.evaluate(input).get();

        assertWithMessage("Evaluation of number is failed.")
                .that(resultHolder.value())
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource({"2 * (5 + 7 * (4 + 1)) + 20, 100.0", "(2 + 1) * (-2), -6.0,", "4 * (0.1 * 100) + 12, 52.0"})
    void testCorrectEvaluationOfExpressionWithBrackets(String input, double expected)
            throws IncorrectFormatOfExpressionException {

        ValueHolder resultHolder = mathCalculator.evaluate(input).get();

        assertWithMessage("Evaluation of number is failed.")
                .that(resultHolder.value())
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"min( max(-5,-6,-7), 2, 3 ) + max(1,2,3) * pow(2,3) : 19.0", "sum(1, 2, 3) : 6.0"}, delimiter = ':')
    void testCorrectEvaluationOfExpressionWithFunctions(String input, double expected)
            throws IncorrectFormatOfExpressionException {

        ValueHolder resultHolder = mathCalculator.evaluate(input).get();

        assertWithMessage("Evaluation of number is failed.")
                .that(resultHolder.value())
                .isEqualTo(expected);
    }

    @ParameterizedTest
    @CsvSource(value = {"3 > 2 : true", "max(max(4,10)) < min(2+2,3+3) * 2 : false"}, delimiter = ':')
    void testCorrectEvaluationOfBooleanExpression(String input, boolean expected)
            throws IncorrectFormatOfExpressionException {

        ValueHolder resultHolder = mathCalculator.evaluate(input).get();

        assertWithMessage("Evaluation of number is failed.")
                .that(resultHolder.value())
                .isEqualTo(expected);
    }


}

