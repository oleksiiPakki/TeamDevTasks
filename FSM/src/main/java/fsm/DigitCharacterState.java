package fsm;

import java.text.CharacterIterator;

public class DigitCharacterState<T extends StringBuilder> extends State<T> {
    private final boolean mayBeFinish;

    DigitCharacterState(boolean mayBeFinish){
        this.mayBeFinish = mayBeFinish;

    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter) {
        char currentCharacterOfSequence = inputSequenceOfCharacter.current();

        if (Character.isDigit(currentCharacterOfSequence)){

            outputSequenceOfCharacter.append(currentCharacterOfSequence);
            inputSequenceOfCharacter.next();

            return true;
        }

        return false;
    }
}
