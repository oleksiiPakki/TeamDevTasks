package io.teamdev.javaclasses.compilertests;

import io.teamdev.javaclasses.impl.abstracts.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.language.AlmostNormalLanguageCompiler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AlmostNormalLanguageCompilerTest {
    private final AlmostNormalLanguageCompiler compiler = new AlmostNormalLanguageCompiler();


    @ParameterizedTest
    @MethodSource("testOfExecutionOfListOfStatements")
    void testCorrectInitialization(String input, String expected)
            throws IncorrectFormatOfExpressionException, ProgramExecutionException {

        assertOutputValue(input, expected,
                "List of statements execution is failed.");
    }

    private static Stream<Arguments> testOfExecutionOfListOfStatements() {
        return Stream.of(
                Arguments.of("max = 3.9543; result = max < min(2+2,3+3)*2; print(result,max);",
                        "true,3.9543"),
                Arguments.of("bigName = 95 + 5; print(max(bigName - 95, 40));", "40.0")

        );
    }

    @ParameterizedTest
    @MethodSource("parsingStatementsNegativeTestCase")
    void testEvaluationOfIncorrectFormatExpression(String input, String expected) {

        assertIncorrectFormatException(input, expected);
    }

    private static Stream<Arguments> parsingStatementsNegativeTestCase() {
        return Stream.of(
                Arguments.of("a = 4 print(a)", "Deadlock on 6 position"),
                Arguments.of("a  4; print(a)", "Deadlock on 3 position"),
                Arguments.of("a = 4; printa)", "Deadlock on 13 position")

        );
    }


    @ParameterizedTest
    @MethodSource("switchOperator")
    void testEvaluationOfIncorrectExpression(String input, String expected)
            throws ProgramExecutionException, IncorrectFormatOfExpressionException {


        assertOutputValue(input, expected, "Executing of switch operator is failed");
    }

    private static Stream<Arguments> switchOperator() {
        return Stream.of(
                Arguments.of("m = 2 + 2 * 2; switch(m) {" +
                        "case 6: {m = pow(m ,2);}" +
                        "case 7: {m = m * 2;}" +
                        "case 8: {m = m / 2;}" +
                        "case 9: {m = m - 2;}" +
                        "default: {print(m / 0);}" +
                        "} " +
                        "print(m);)", "36.0"),

                Arguments.of("m = pow(2,3); n = pow(3,2); switch(m | n) {" +
                        "case 6: {m = pow(m ,2);}" +
                        "case 7: {m = m * 2;}" +
                        "case 8: {m = m / 2;}" +
                        "default: {print(m / 0);}" +
                        "} " +
                        "print(m, n);)", "4.0,9.0"),

                Arguments.of("m = pow(2,3); n = pow(3,2); switch(m | n) {" +
                        "case 6: {m = pow(m ,2);}" +
                        "case 7: {m = m * 2;}" +
                        "case 9: {m = n * 3;}" +
                        "default: {print(m / 0);}" +
                        "} " +
                        "print(m, n);)", "27.0,9.0"),


                Arguments.of("m = pow(2,3); n = pow(3,2); switch(m | n) {" +
                        "case 6: {m = pow(m ,2);}" +
                        "case 7: {m = m * 2;}" +
                        "case 10: {m = n * 3;}" +
                        "default: {m = 0; n = 0;}" +
                        "} " +
                        "print(m, n);)", "0.0,0.0")

        );
    }

    private void assertOutputValue(String input, String expected, String message)
            throws IncorrectFormatOfExpressionException, ProgramExecutionException {

        RuntimeEnvironment environment = new RuntimeEnvironment();

        compiler.evaluate(input, environment);

        assertWithMessage(message)
                .that(environment.output().toString())
                .isEqualTo(expected);
    }

    private void assertIncorrectFormatException(String program, String expectedMassage) {
        RuntimeEnvironment environment = new RuntimeEnvironment();

        IncorrectFormatOfExpressionException ex =
                assertThrows(IncorrectFormatOfExpressionException.class, () ->

                        compiler.evaluate(program, environment));
        assertThat(ex).hasMessageThat()
                .contains(expectedMassage);
    }


}
