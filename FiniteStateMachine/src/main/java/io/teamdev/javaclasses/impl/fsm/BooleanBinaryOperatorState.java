package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.math.BooleanBinaryOperator;
import io.teamdev.javaclasses.impl.math.GreaterThanBinaryOperator;
import io.teamdev.javaclasses.impl.math.LessThanBinaryOperator;

import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BooleanBinaryOperatorState<T extends List<Command>> extends State<T> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    private final Map<Character, BooleanBinaryOperator<Double>> operators = new HashMap<>();

    BooleanBinaryOperatorState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    {
        operators.put('>', new GreaterThanBinaryOperator<>(1));
        operators.put('<', new LessThanBinaryOperator<>(1));
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

        Optional<BooleanBinaryOperator<Double>> currentOperator = defineBinaryOperator(
                currentCharacter);

        if (currentOperator.isPresent()) {

            outputSequence.add(environment -> environment.topStack()
                                                         .pushOperator(currentOperator.get()));

            inputSequence.next();

            return true;
        }

        return false;
    }

    private Optional<BooleanBinaryOperator<Double>> defineBinaryOperator(
            Character currentCharacter) {

        return operators.containsKey(currentCharacter) ? Optional.of(
                operators.get(currentCharacter))
                                                       : Optional.empty();
    }
}
