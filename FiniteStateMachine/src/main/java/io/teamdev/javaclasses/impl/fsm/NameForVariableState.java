package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.List;

public class NameForVariableState<T extends List<Command>> extends State<T> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    public NameForVariableState(boolean mayBeFinish, boolean isLexeme) {
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
        char currentCharacter = inputSequence.current();

        if (Character.isLetter(currentCharacter)) {

            try {

                StringBuilder possibleNameOfVariable = new StringBuilder();

                int positionBeforeParsingNameOfVariable = inputSequence.getIndex();

                boolean isSuccess = new NameFiniteStateMachine().run(inputSequence, possibleNameOfVariable);

                if (isSuccess) {

                    if (inputSequence.current() == '('){

                        inputSequence.setIndex(positionBeforeParsingNameOfVariable);

                        return false;
                    }

                    outputSequence.add(
                            environment -> environment.keepVariable(possibleNameOfVariable.toString()));
                }

                return isSuccess;

            } catch (IncorrectFormatOfExpressionException ex) {
                ex.getCause();
            }
        }
        return false;
    }
}
