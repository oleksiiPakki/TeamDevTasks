package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.math.BooleanBinaryOperator;
import io.teamdev.javaclasses.impl.math.EqualsToBinaryOperator;
import io.teamdev.javaclasses.impl.math.GreaterThanBinaryOperator;
import io.teamdev.javaclasses.impl.math.GreaterThanOrEqualsToBinaryOperator;
import io.teamdev.javaclasses.impl.math.LessThanBinaryOperator;
import io.teamdev.javaclasses.impl.math.LessThanOrEqualsToBinaryOperator;
import io.teamdev.javaclasses.impl.math.NotEqualsToBinaryOperator;

import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class BooleanBinaryOperatorState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    private final Map<String, BooleanBinaryOperator<Double>> operators = new HashMap<>();

    BooleanBinaryOperatorState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    {
        operators.put(">", new GreaterThanBinaryOperator(1));
        operators.put(">=", new GreaterThanOrEqualsToBinaryOperator(1));
        operators.put("<", new LessThanBinaryOperator<>(1));
        operators.put("<=", new LessThanOrEqualsToBinaryOperator(1));
        operators.put("==", new EqualsToBinaryOperator(1));
        operators.put("!=", new NotEqualsToBinaryOperator(1));

    }

    enum PossibleSignsOfBooleanOperators {

        GREATER_SIGN('>'),
        LESS_SIGN('<'),
        EQUALS_SIGN('='),
        NOT_EQUALS_SIGN('!');

        private final Character sign;

        PossibleSignsOfBooleanOperators(Character sign) {
            this.sign = sign;
        }

        public Character containingChar() {
            return sign;
        }
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
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) throws
                                                                             IncorrectFormatOfExpressionException {

        StringBuilder possibleNameOfOperator = new StringBuilder();

        while (isBooleanOperatorSign(inputSequence.current())) {

            possibleNameOfOperator.append(inputSequence.current());

            inputSequence.next();
        }

        Optional<BooleanBinaryOperator<Double>> currentOperator = defineBinaryOperator(
                possibleNameOfOperator.toString());

        if (currentOperator.isPresent()) {

            outputSequence.add(environment -> environment.topStack()
                                                         .pushOperator(currentOperator.get()));

            inputSequence.next();

            return true;
        }

        return false;
    }

    private Optional<BooleanBinaryOperator<Double>> defineBinaryOperator(
            String possibleNameOfOperator) {

        return operators.containsKey(possibleNameOfOperator) ? Optional.of(
                operators.get(possibleNameOfOperator)) : Optional.empty();
    }

    private boolean isBooleanOperatorSign(Character currentSign) {

        for (PossibleSignsOfBooleanOperators possibleSign : PossibleSignsOfBooleanOperators.values()) {

            if (possibleSign.sign == currentSign) {

                return true;
            }
        }

        return false;
    }
}
