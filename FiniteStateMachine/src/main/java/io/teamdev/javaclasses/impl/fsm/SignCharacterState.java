package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstracts.State;

import java.text.CharacterIterator;

public class SignCharacterState extends State<StringBuilder> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    final private Character requiredCharacter;


    public SignCharacterState(boolean mayBeFinish, boolean isLexeme, Character requiredCharacter) {
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
    public boolean accept(CharacterIterator inputSequence, StringBuilder outputSequence) {

        Character currentCharacter = inputSequence.current();

        if (currentCharacter.equals(requiredCharacter)) {


            outputSequence.append(currentCharacter);
            inputSequence.next();

            return true;
        }

        return false;
    }
}
