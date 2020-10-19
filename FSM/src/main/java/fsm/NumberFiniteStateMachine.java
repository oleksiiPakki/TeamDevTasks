package fsm;

import math.ShuntingYard;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class NumberFiniteStateMachine<T extends StringBuilder> extends FiniteStateMachine<T>{

    public NumberFiniteStateMachine(){
        State negativeNumberSignState = new SignCharacterState(false, '-');
        State integerDigitState = new DigitCharacterState(true);
        State decimalPoint = new SignCharacterState(false, '.');
        State decimalDigitState = new DigitCharacterState(true);

        negativeNumberSignState.addTransition(integerDigitState);
        integerDigitState.addTransition(integerDigitState);
        integerDigitState.addTransition(decimalPoint);
        decimalPoint.addTransition(decimalDigitState);
        decimalDigitState.addTransition(decimalDigitState);

        setStartedStates(negativeNumberSignState, integerDigitState);
    }

    @Override
    double resultOfExecution(CharacterIterator inputSequence, T outputSequence) throws IncorrectFormatOfExpressionException {
        StringBuilder result = new StringBuilder("");
        NumberFiniteStateMachine machine = new NumberFiniteStateMachine<>();

        machine.run(inputSequence, result);

        return Double.parseDouble(result.toString());
    }
}
