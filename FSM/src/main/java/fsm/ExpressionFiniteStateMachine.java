package fsm;

import math.ShuntingYard;

import java.text.CharacterIterator;

public class ExpressionFiniteStateMachine<T extends ShuntingYard> extends FiniteStateMachine<T> {
    public ExpressionFiniteStateMachine(){
        State numberState = new NumberState(true);
        State binaryOperationState = new BinaryOperatorState(false);

        numberState.addTransition(binaryOperationState);
        binaryOperationState.addTransition(numberState);

        setStartedStates(numberState);
    }

    @Override
    public double resultOfExecution(CharacterIterator inputSequence, T outputSequence)
            throws IncorrectFormatOfExpressionException {

        ShuntingYard shuntingYard = new ShuntingYard();

        ExpressionFiniteStateMachine finiteStateMachine = new ExpressionFiniteStateMachine();
        finiteStateMachine.run(inputSequence, shuntingYard);

        return shuntingYard.popAllOperators();
    }
}
