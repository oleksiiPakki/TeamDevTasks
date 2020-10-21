import io.javaclasses.pakki.calculator.fsm.IncorrectFormatOfExpressionException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@RunWith(Parameterized.class)
public class MathExpressionCalculatorTest {
    private final String expression;
    private final double expectResult;
    private final MathExpressionCalculator calculator = new MathExpressionCalculator();

    public MathExpressionCalculatorTest(String expression, double expectResult) {
        this.expression = expression;
        this.expectResult = expectResult;
    }

    @Parameterized.Parameters
    public static Collection<Object> data() {
        return Arrays.asList(new Object[][]{
                {"123", 123.0},
                {"-0.99", -0.99},
                {"123.456", 123.456},
                {"2*2", 4},
                {"2+2*2", 6},
                {"1.7+0.3+4*5", 22},

        });
    }

    @Test
    public void testEvaluationOfMathExpression()
            throws IncorrectFormatOfExpressionException {

        assertThat(expectResult).isEqualTo(calculator.evaluate(expression));


    }

    @Test
    public void testDefineIncorrectMathExpression() {
        IncorrectFormatOfExpressionException ex = assertThrows(IncorrectFormatOfExpressionException.class,
                () -> calculator.evaluate("-123*5+3+c"),
                "expected calculator() to throw , but it didn't"
        );

        assertTrue(ex.getMessage().contains("You have incorrect format of expression - deadlock or not started"));
    }

    @Test
    public void testDefineEmptyMathExpressions() {
        IncorrectFormatOfExpressionException ex = assertThrows(IncorrectFormatOfExpressionException.class,
                () -> calculator.evaluate(""),
                "expected calculator() to throw , but it didn't"
        );

        assertTrue(ex.getMessage().contains("You have incorrect format of expression - deadlock or not started"));
    }




}
