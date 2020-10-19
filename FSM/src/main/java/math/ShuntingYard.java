package math;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Queue;

public class ShuntingYard {
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

        System.out.println(operators + "op");
        BinaryOperator binaryOperator = operators.pop();

        double result = binaryOperator.execute(leftOperand, rightOperand);

        operands.push(result);
    }

}
