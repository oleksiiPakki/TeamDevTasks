import io.javaclasses.pakki.calculator.fsm.ExpressionFiniteStateMachine;
import io.javaclasses.pakki.calculator.fsm.IncorrectFormatOfExpressionException;
import io.javaclasses.pakki.calculator.math.ShuntingYard;
import org.apache.log4j.Logger;

import java.text.StringCharacterIterator;


public class MathExpressionCalculator {
    private static final Logger logger = Logger.getLogger(ExpressionFiniteStateMachine.class);

    public double evaluate(String mathExpression) throws IncorrectFormatOfExpressionException {
        if (logger.isInfoEnabled()) {
            logger.info("Evaluating started" + "\n");
        }

        ShuntingYard shuntingYard = new ShuntingYard();

        new ExpressionFiniteStateMachine<>().run(new StringCharacterIterator(mathExpression), shuntingYard);

        if (logger.isInfoEnabled()) {
            logger.info("Evaluating finished" + "\n");
        }

        return shuntingYard.popAllOperators();
    }
}
