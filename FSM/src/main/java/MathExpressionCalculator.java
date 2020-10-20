import fsm.ExpressionFiniteStateMachine;
import fsm.IncorrectFormatOfExpressionException;
import math.ShuntingYard;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

public class MathExpressionCalculator {
    public static void main(String[] args) {
        String expression = "2*3-4+5";
        expression.replaceAll(" ", "");

        double result = Double.MAX_VALUE;
        ExpressionFiniteStateMachine finiteStateMachine = new ExpressionFiniteStateMachine();

        try {

             result = finiteStateMachine.resultOfExecution(new StringCharacterIterator("1.7+0.3+4*5"),
                    new ShuntingYard());

        }catch (IncorrectFormatOfExpressionException ex){
            System.out.println(ex.getCause());
        }

        System.out.println(result);
    }
}
