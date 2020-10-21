package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.ShuntingYard;

import java.text.CharacterIterator;

public class NumberState<T extends ShuntingYard> extends State<T> {
    private final boolean mayBeFinish;

    NumberState(boolean mayBeFinish) {
        this.mayBeFinish = mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter) {
        try {
            StringBuilder currentPartOfExpression = new StringBuilder();

            new NumberFiniteStateMachine<>().run(inputSequenceOfCharacter, currentPartOfExpression);

            double resultNumber = Double.parseDouble(currentPartOfExpression.toString());

            outputSequenceOfCharacter.pushOperand(resultNumber);

            return true;

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.printStackTrace();
        }

        return false;
    }

    public boolean mayBeFinish() {
        return mayBeFinish;
    }
}

