package fsm;

import math.ShuntingYard;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class ExpressionFiniteStateMachine<T extends ShuntingYard> extends FiniteStateMachine<T> {
    ExpressionFiniteStateMachine(){
        State numberState = new NumberState(true);
        State binaryOperationState = new BinaryOperatorState(false);

        numberState.addTransition(binaryOperationState);
        binaryOperationState.addTransition(numberState);

        setStartedStates(numberState);
    }

    public static void main(String[] args) throws IncorrectFormatOfExpressionException {
        FiniteStateMachine finiteStateMachine = new ExpressionFiniteStateMachine();
        System.out.println(finiteStateMachine.resultOfExecution(new StringCharacterIterator("1.7+0.3+4*5"),
                new ShuntingYard()));
    }


    @Override
    double resultOfExecution(CharacterIterator inputSequence, T outputSequence)
            throws IncorrectFormatOfExpressionException {

        ShuntingYard shuntingYard = new ShuntingYard();

        ExpressionFiniteStateMachine finiteStateMachine = new ExpressionFiniteStateMachine();
        finiteStateMachine.run(inputSequence, shuntingYard);

        return shuntingYard.popAllOperators();
    }
}
