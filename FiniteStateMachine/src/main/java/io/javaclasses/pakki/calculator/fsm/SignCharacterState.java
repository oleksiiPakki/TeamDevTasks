package io.javaclasses.pakki.calculator.fsm;

import org.apache.log4j.Logger;

import java.text.CharacterIterator;

public class SignCharacterState<T extends StringBuilder> extends State<T> {
    private static final Logger logger = Logger.getLogger(SignCharacterState.class);

    final private Character requiredCharacter;
    final private boolean mayBeFinish;

    public SignCharacterState(boolean mayBeFinish, Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.requiredCharacter = requiredCharacter;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequence) {

        Character currentCharacter = inputSequenceOfCharacter.current();

        if (currentCharacter.equals(requiredCharacter)) {
            if (logger.isTraceEnabled()){
                logger.trace("Fsm in a SignCharacterState:" + inputSequenceOfCharacter.current() + "\n");

            }

            outputSequence.append(currentCharacter);
            inputSequenceOfCharacter.next();

            return true;
        }

        return false;
    }
}
