package fsm;

import math.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Optional;

public class Test {
    private final Deque<Double> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> operators = new ArrayDeque();

    public void pushOperand(double operand) {
        operands.push(operand);
        System.out.println(operands);
    }

    public void pushOperator(BinaryOperator operator) {

        if (operators.size() > 0) {
            if (operator.priority() <= operators.peek().priority()) {
                theResultOfExecutionOfPastTwoOperandsWithPastOperator();
                pushOperator(operator);
                return;
            }
        }

        operators.push(operator);
        System.out.println(operator);

    }


    public double popAllOperators() {

        while (!operators.isEmpty()) {

            theResultOfExecutionOfPastTwoOperandsWithPastOperator();
        }

        return operands.pop();
    }

    public void theResultOfExecutionOfPastTwoOperandsWithPastOperator() {
        double rightOperand = operands.pop();
        double leftOperand = operands.pop();

        BinaryOperator binaryOperator = operators.pop();

        double result = binaryOperator.execute(leftOperand, rightOperand);

        operands.push(result);
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

    public static void main(String[] args) {
        String string = "2+3*4+5-1*5";
        Test test = new Test();

        for (int i = 0; i < string.length(); i++) {
            if (Character.isDigit(string.charAt(i))) {
                test.pushOperand(Double.parseDouble(Character.toString(string.charAt(i))));
            } else {
                Optional<BinaryOperator> binaryOperator = test.defineBinaryOperator(string.charAt(i));
                test.pushOperator(binaryOperator.get());
            }
        }

        System.out.println(test.popAllOperators());
    }
}
