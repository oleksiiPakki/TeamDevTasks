package fsm;

import math.ShuntingYard;

import java.text.CharacterIterator;

public class NumberState<T extends ShuntingYard> extends State<T> {
    private final boolean mayBeFinish;

    NumberState(boolean mayBeFinish){
        this.mayBeFinish = mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter) {
        NumberFiniteStateMachine numberFiniteStateMachine = new NumberFiniteStateMachine();
        double resultNumber = Double.MAX_VALUE;

        try {
            StringBuilder currentPartOfExpression = new StringBuilder("");
            resultNumber = numberFiniteStateMachine.resultOfExecution(inputSequenceOfCharacter, currentPartOfExpression);

            if (resultNumber < Double.MAX_VALUE) {
                outputSequenceOfCharacter.pushOperand(resultNumber);
                return true;
            }
        } catch (IncorrectFormatOfExpressionException ex) {
            System.out.println(ex.getCause());
        }

        return false;
    }

    public boolean mayBeFinish(){
        return mayBeFinish;
    }
}

