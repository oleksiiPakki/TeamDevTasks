package io.javaclasses.pakki.calculator.fsm;
import java.util.Arrays;


public class NumberFiniteStateMachine<T extends StringBuilder> extends FiniteStateMachine<T>{

    public NumberFiniteStateMachine(){
        State<T> negativeNumberSignState = new SignCharacterState<>(false, '-');
        State<T> integerDigitState = new DigitCharacterState<>(true);
        State<T> decimalPoint = new SignCharacterState<>(false, '.');
        State<T> decimalDigitState = new DigitCharacterState<>(true);

        negativeNumberSignState.addTransition(integerDigitState);
        integerDigitState.addTransition(integerDigitState);
        integerDigitState.addTransition(decimalPoint);
        decimalPoint.addTransition(decimalDigitState);
        decimalDigitState.addTransition(decimalDigitState);

        setStartedStates(Arrays.asList(negativeNumberSignState,integerDigitState));

    }

}
