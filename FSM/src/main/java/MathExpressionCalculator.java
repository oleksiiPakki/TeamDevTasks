import io.javaclasses.pakki.calculator.fsm.ExpressionFiniteStateMachine;
import io.javaclasses.pakki.calculator.fsm.IncorrectFormatOfExpressionException;
import io.javaclasses.pakki.calculator.fsm.NumberFiniteStateMachine;
import io.javaclasses.pakki.calculator.math.ShuntingYard;

import java.text.StringCharacterIterator;


public class MathExpressionCalculator {
    public double evaluate(String mathExpression) throws IncorrectFormatOfExpressionException {

        ShuntingYard shuntingYard = new ShuntingYard();

        new ExpressionFiniteStateMachine<>().run(new StringCharacterIterator(mathExpression), shuntingYard);

        return shuntingYard.popAllOperators();
    }
}
