package fsm;

import math.*;

import java.text.CharacterIterator;
import java.util.Optional;

public class BinaryOperatorState<T extends ShuntingYard> extends State<T> {
    private final boolean mayBeFinish;

    BinaryOperatorState(boolean mayBeFinish){
        this.mayBeFinish = mayBeFinish;
    }


    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter) {
        Character currentCharacter = inputSequenceOfCharacter.current();
        Optional<BinaryOperator> currentOperator = defineBinaryOperator(currentCharacter);

        if (currentOperator.isPresent()) {
            outputSequenceOfCharacter.pushOperator(currentOperator.get());
            inputSequenceOfCharacter.next();

            return true;
        }

        return false;
    }

    private Optional<BinaryOperator> defineBinaryOperator(Character currentCharacter) {
        switch (currentCharacter) {
            case '+': {
                return Optional.of(new AdditionOperator(1));
            }

            case '-': {
                return Optional.of(new SubtractionOperator(1));
            }

            case '/': {
                return Optional.of(new DivisionOperator(2));
            }

            case '*': {
                return Optional.of(new MultiplyingOperator(2));
            }
        }

        return Optional.empty();
    }
}
