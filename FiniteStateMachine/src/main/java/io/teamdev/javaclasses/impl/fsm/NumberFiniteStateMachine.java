package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class NumberFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    private static final Logger logger = Logger.getLogger(NumberFiniteStateMachine.class);

    public NumberFiniteStateMachine() {

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine started" + "\n");
        }

        State<StringBuilder> negativeNumberSignState = new SignCharacterState(false, false, '-');
        State<StringBuilder> integerDigitState = new DigitCharacterState(true, false);
        State<StringBuilder> decimalPointState = new SignCharacterState(false, false, '.');
        State<StringBuilder> decimalDigitState = new DigitCharacterState(true, false);

        negativeNumberSignState.addTransition(integerDigitState);

        integerDigitState.addTransition(integerDigitState);
        integerDigitState.addTransition(decimalPointState);

        decimalPointState.addTransition(decimalDigitState);
        decimalDigitState.addTransition(decimalDigitState);

        setStartedStates(Arrays.asList(negativeNumberSignState, integerDigitState));

        if (logger.isInfoEnabled()) {
            logger.info("Number Finite machine finished" + "\n");
        }

    }

    public Optional<List<Command>> number(CharacterIterator input) {
        List<Command> resultCommands = new ArrayList<>();

        StringBuilder number = new StringBuilder();

        try {
            boolean isSuccess = run(input, number);

            if (isSuccess) {
                double resultNumber = Double.parseDouble(number.toString());

                resultCommands.add(environment -> environment.topStack()
                                                             .pushOperand(new DoubleValueHolder(
                                                                     resultNumber)));

                return Optional.of(resultCommands);
            }

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return Optional.empty();
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return number(inputSequence);
    }
}
