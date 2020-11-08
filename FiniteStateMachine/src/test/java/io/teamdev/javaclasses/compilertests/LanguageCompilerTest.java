package io.teamdev.javaclasses.compilertests;

import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.language.LanguageCompiler;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.google.common.truth.Truth.assertWithMessage;

public class LanguageCompilerTest {
    private final LanguageCompiler languageCompiler = new LanguageCompiler();

    @ParameterizedTest
    @CsvSource(value = {"max = 4.5345; result = max(max,4,10) < min(2+2,3+3) * 2; " +
            "print(result); : false",
            "resultOfComparing = max(1,2,3) != max(1,2,4); print(resultOfComparing); : true",
            "bigName = 95 + 5;" +
                    "print(max(bigName - 95, 40)); : 40.0"}, delimiter = ':')
    void testCorrectEvaluationOfBooleanExpression(String input, String expected)
            throws IncorrectFormatOfExpressionException {
        {
            RuntimeEnvironment environment = new RuntimeEnvironment();

            languageCompiler.evaluate(input, environment);

            String result = environment.output().toString();
            assertWithMessage("Calculation of mathematical expression with brackets is failed.")
                    .that(result)
                    .isEqualTo(expected);
        }
    }

}
