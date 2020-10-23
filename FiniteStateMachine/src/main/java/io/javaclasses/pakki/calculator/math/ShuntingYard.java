package io.javaclasses.pakki.calculator.math;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Class for representing shunting yard for operand and operators
 */
public class ShuntingYard {
    private final Deque<Double> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> operators = new ArrayDeque<>();

    /**
     *
     * @param operand operand to push in stack of operands
     */
    public void pushOperand(double operand) {
        operands.push(operand);

    }

    /**
     *
     * @param operator operator to push in stack of operators
     */
    public void pushOperator(BinaryOperator operator) {

        if (operators.peek() != null) {
            //if priority of current operator is not bigger,
            // than priority of peek operator in stack,
            // we execute last two operands with peek operator and push it back
            if (operators.peek().priority() >= operator.priority()) {

                theResultOfExecutionOfPastTwoOperandsWithPastOperator();

                pushOperator(operator);

                return;
            }
        }

        operators.push(operator);

    }

    /**
     *
     * @return result of execution
     */
    public double popAllOperators() {

        while (!operators.isEmpty()) {

            theResultOfExecutionOfPastTwoOperandsWithPastOperator();
        }
        return operands.pop();
    }

    private void theResultOfExecutionOfPastTwoOperandsWithPastOperator() {
        double rightOperand = operands.pop();
        double leftOperand = operands.pop();

        BinaryOperator binaryOperator = operators.pop();

        double result = binaryOperator.execute(leftOperand, rightOperand);

        operands.push(result);
    }

}
