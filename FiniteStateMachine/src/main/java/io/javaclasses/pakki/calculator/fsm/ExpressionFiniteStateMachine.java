package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.ShuntingYard;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Implementation of fsm
 * Calculate a math expression represented by string
 *
 * @param <T> define the class, representing the result of execution
 */
public class ExpressionFiniteStateMachine<T extends ShuntingYard> extends FiniteStateMachine<T> {
    private static final Logger logger = Logger.getLogger(ExpressionFiniteStateMachine.class);

    public ExpressionFiniteStateMachine() {

        if (logger.isInfoEnabled()) {
            logger.info("Expression Finite machine started" + "\n");
        }

        State<T> numberState = new NumberState<>(true);
        State<T> binaryOperationState = new BinaryOperatorState<>(false);
        State<T> bracketsState = new BracketsState<>(true, '(');

        numberState.addTransition(binaryOperationState);
        numberState.addTransition(bracketsState);
        bracketsState.addTransition(binaryOperationState);
        bracketsState.addTransition(bracketsState);
        binaryOperationState.addTransition(bracketsState);
        binaryOperationState.addTransition(numberState);

        setStartedStates(Arrays.asList(bracketsState, numberState));

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine finished" + "\n");
        }
    }

}
