package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.*;

import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BinaryOperatorState<T extends ShuntingYard> extends State<T> {
    private final boolean mayBeFinish;
    Map<Character, BinaryOperator> operators = new HashMap<>();

    BinaryOperatorState(boolean mayBeFinish){
        this.mayBeFinish = mayBeFinish;
    }

    {
        operators.put('+', new AdditionOperator(1));
        operators.put('-', new SubtractionOperator(1));
        operators.put('*', new MultiplyingOperator(2));
        operators.put('/', new DivisionOperator(2));
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

    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    private Optional<BinaryOperator> defineBinaryOperator(Character currentCharacter) {
        return operators.containsKey(currentCharacter) ? Optional.of(operators.get(currentCharacter))
                : Optional.empty();
    }
}
