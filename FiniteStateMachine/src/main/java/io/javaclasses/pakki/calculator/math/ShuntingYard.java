package io.javaclasses.pakki.calculator.math;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShuntingYard {
    private final Deque<Double> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> operators = new ArrayDeque<>();

    public void pushOperand(double operand) {
        operands.push(operand);

    }

    public void pushOperator(BinaryOperator operator) {

        if (operators.peek() != null) {
            if (operators.peek().priority() >= operator.priority()) {

                theResultOfExecutionOfPastTwoOperandsWithPastOperator();

                pushOperator(operator);

                return;
            }
        }

        operators.push(operator);

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

}
