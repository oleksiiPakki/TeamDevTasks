package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.ShuntingYard;

import java.util.Collections;

public class ExpressionFiniteStateMachine<T extends ShuntingYard> extends FiniteStateMachine<T> {
    public ExpressionFiniteStateMachine(){
        State<T> numberState = new NumberState<>(true);
        State<T> binaryOperationState = new BinaryOperatorState<>(false);

        numberState.addTransition(binaryOperationState);
        binaryOperationState.addTransition(numberState);

        setStartedStates(Collections.singletonList(numberState));
    }

}
