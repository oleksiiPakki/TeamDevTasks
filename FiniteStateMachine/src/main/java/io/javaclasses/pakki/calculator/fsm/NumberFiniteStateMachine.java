package io.javaclasses.pakki.calculator.fsm;

import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Implementation of fsm
 * Define a number, represented by string
 *
 * @param <T> define the class, representing the result of execution
 */
public class NumberFiniteStateMachine<T extends StringBuilder> extends FiniteStateMachine<T> {
    private static final Logger logger = Logger.getLogger(ExpressionFiniteStateMachine.class);

    public NumberFiniteStateMachine() {

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine started" + "\n");
        }

        State<T> negativeNumberSignState = new SignCharacterState<>(false, '-');
        State<T> integerDigitState = new DigitCharacterState<>(true);
        State<T> decimalPoint = new SignCharacterState<>(false, '.');
        State<T> decimalDigitState = new DigitCharacterState<>(true);

        negativeNumberSignState.addTransition(integerDigitState);
        integerDigitState.addTransition(integerDigitState);
        integerDigitState.addTransition(decimalPoint);
        decimalPoint.addTransition(decimalDigitState);
        decimalDigitState.addTransition(decimalDigitState);

        setStartedStates(Arrays.asList(negativeNumberSignState, integerDigitState));

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine finished" + "\n");
        }

    }


}
