package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.util.List;

public class AssignmentCharacterState<T extends List<Command>> extends State<T> {

    private final boolean mayBeFinish;

    private final boolean isLexeme;

    private final Character requiredCharacter;

    AssignmentCharacterState(boolean mayBeFinish, boolean isLexeme,
                             Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
        this.requiredCharacter = requiredCharacter;
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
        Character currentCharacter = inputSequence.current();

        if (requiredCharacter.equals(currentCharacter)) {
            outputSequence.add(RuntimeEnvironment::isInitializing);

            inputSequence.next();

            return true;
        }

        return false;
    }
}
