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
                {"2+3*(3+1)",14},
                {"(3+1)*2",8},
                {"20+2*(5+7*(4+1))",  100},
                {"2*(5+7*(4+1))+20",  100},
                {"5.01-7*8/2-11.01", -34},
                {"min(1,2,3) + max(4,5,6,7)", 8},
                {"sin(0) + cos(0)", 1}

        });
    }

    @Test
    public void testEvaluationOfMathExpression()
            throws IncorrectFormatOfExpressionException {

        assertThat(expectResult).isEqualTo(calculator.evaluate(expression));
    }

    @Test
    public void testDefineIncorrectMathExpressionWithMissingNumber() {
        IncorrectFormatOfExpressionException ex = assertThrows(IncorrectFormatOfExpressionException.class,
                () -> calculator.evaluate("(-123*+3+c)"),
                "expected calculator() to throw , but it didn't"
        );

        assertTrue(ex.getMessage().contains("You have incorrect format of expression"));
    }

    @Test
    public void testDefineEmptyMathExpressions() {
        IncorrectFormatOfExpressionException ex = assertThrows(IncorrectFormatOfExpressionException.class,
                () -> calculator.evaluate(""),
                "expected calculator() to throw , but it didn't"
        );

        assertTrue(ex.getMessage().contains("You have incorrect format of expression"));
    }

    @Test
    public void testDefineEmptyBracketsMathExpressions() {
        IncorrectFormatOfExpressionException ex = assertThrows(IncorrectFormatOfExpressionException.class,
                () -> calculator.evaluate("1+2*()-1"),
                "expected calculator() to throw , but it didn't"
        );

        assertTrue(ex.getMessage().contains("You have incorrect format of expression"));
    }

    @Test
    public void testDefineIncorrectBracketsExpressionMathExpressions() {
        IncorrectFormatOfExpressionException ex = assertThrows(IncorrectFormatOfExpressionException.class,
                () -> calculator.evaluate("1+2*()-1)"),
                "expected calculator() to throw, but it didn't"
        );

        assertTrue(ex.getMessage().contains("You have incorrect format of expression"));
    }





}
