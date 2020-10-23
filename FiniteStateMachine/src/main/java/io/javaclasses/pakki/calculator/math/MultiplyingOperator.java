package io.javaclasses.pakki.calculator.math;

import org.apache.log4j.Logger;

/**
 * Operator of multiplying of two operands
 */
public class MultiplyingOperator implements BinaryOperator {
    private static final Logger logger = Logger.getLogger(MultiplyingOperator.class);

    private final int priority;

    public MultiplyingOperator(int priority) {
        this.priority = priority;
    }


    /**
     *
     * @param leftOperand Value of the left operand
     * @param rightOperand Value of the right operand
     * @return result of multiplying left and right operands
     */
    @Override
    public double execute(double leftOperand, double rightOperand) {
        if (logger.isTraceEnabled()){
            logger.trace("Starting execution of operands:" + leftOperand + "+" + rightOperand);
        }

        return leftOperand * rightOperand;
    }

    /**
     *
     * @return priority of execution of adding operator (priority is 1)
     */
    public int priority() {
        return priority;
    }

}
