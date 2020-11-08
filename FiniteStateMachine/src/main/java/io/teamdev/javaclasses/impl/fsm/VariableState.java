package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.util.List;

public class VariableState<T extends List<Command>> extends State<T> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    VariableState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {
        try {
            StringBuilder possibleNameOfVariable = new StringBuilder();

            int positionBeforeParsingNameOfVariable = inputSequence.getIndex();

            boolean isSuccess = new NameFiniteStateMachine().run(inputSequence,
                                                                   possibleNameOfVariable);

            if (isSuccess) {

                if (inputSequence.current() == '(') {

                    inputSequence.setIndex(positionBeforeParsingNameOfVariable);

                    return false;
                }

                outputSequence.add(environment -> {
                    environment.startNewStack();

                    ValueHolder valueOfVariable = environment.value(
                            possibleNameOfVariable.toString());

                    environment.topStack()
                               .pushOperand(valueOfVariable);

                });
            }

            return isSuccess;

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return false;
    }
}
