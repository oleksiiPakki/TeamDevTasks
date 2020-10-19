package fsm;

import java.text.CharacterIterator;

public class SignCharacterState<T extends StringBuilder> extends State<T> {
    final private Character requiredCharacter;
    final private boolean mayBeFinish;

    SignCharacterState(boolean mayBeFinish, Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.requiredCharacter = requiredCharacter;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter) {
        Character currentCharacter = inputSequenceOfCharacter.current();

        if (currentCharacter.equals(requiredCharacter)) {

            outputSequenceOfCharacter.append(currentCharacter);
            inputSequenceOfCharacter.next();

            return true;
        }

        return false;
    }
}
