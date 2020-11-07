package io.teamdev.javaclasses.impl.runtime;

import io.teamdev.javaclasses.impl.fsm.IncorrectBracketsExpressionException;
import io.teamdev.javaclasses.impl.math.BinaryOperator;

import java.util.*;

public class ShuntingYard {

    private int countOfBracketsDiscrepancy = 0;

    private final Deque<ValueHolder> operands = new ArrayDeque<>();
    private final Deque<BinaryOperator> binaryOperators = new ArrayDeque<>();

    /**
     * @param operand
     *         operand to push in stack of operands
     */
    public void pushOperand(ValueHolder operand) {

        operands.push(operand);

    }

    /**
     * @param binaryOperator
     *         operator to push in stack of operators
     */

    public void pushOperator(BinaryOperator binaryOperator) {

        if (binaryOperators.peek() != null) {
            //if priority of current operator is not bigger,
            // than priority of peek operator in stack,
            // we execute last two operands with peek operator and push it back
            if (binaryOperators.peek()
                               .priority() >= binaryOperator.priority()) {

                popTopOperator();

                pushOperator(binaryOperator);

                return;
            }
        }

        binaryOperators.push(binaryOperator);
    }

    public void pushOpeningBracket() {

        countOfBracketsDiscrepancy++;

    }

    public void pushClosingBracket() {

        countOfBracketsDiscrepancy--;
    }

    /**
     * @return result of execution
     */
    public Optional<ValueHolder> getResult() throws IncorrectBracketsExpressionException {

        if (countOfBracketsDiscrepancy != 0) {
            throw new IncorrectBracketsExpressionException("Incorrect brackets expression");
        }

        while (!binaryOperators.isEmpty()) {

            popTopOperator();
        }

        return Optional.of(operands.pop());
    }

    private void popTopOperator() {
        ValueHolder rightOperand = operands.pop();
        ValueHolder leftOperand = operands.pop();

        BinaryOperator binaryOperator = binaryOperators.pop();

        ValueHolder result;

        result = binaryOperator.execute(leftOperand, rightOperand);

        if (result != null) {
            operands.push(result);
        }
    }

}
