package io.javaclasses.pakki.calculator.math;

import org.apache.log4j.Logger;

/**
 * Operator of division of two operands
 */
public class DivisionOperator implements BinaryOperator {
    private static final Logger logger = Logger.getLogger(DivisionOperator.class);

    private final int priority;

    public DivisionOperator(int priority){
        this.priority = priority;
    }

    /**
     *
     * @param leftOperand Value of the left operand
     * @param rightOperand Value of the right operand
     * @return result of dividing left and right operands
     */
    @Override
    public double execute(double leftOperand, double rightOperand) {
        if (logger.isTraceEnabled()){
            logger.trace("Starting execution of operands:" + leftOperand + "/" + rightOperand);
        }

        return leftOperand / rightOperand;
    }

    /**
     *
     *  @return priority of execution of dividing operator (priority is 2)
     */
    public int priority(){
        return priority;
    }
}
