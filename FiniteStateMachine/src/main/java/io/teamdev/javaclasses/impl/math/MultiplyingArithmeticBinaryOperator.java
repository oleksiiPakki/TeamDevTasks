package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;
import org.apache.log4j.Logger;

/**
 * Operator of multiplying of two operands
 */
public class MultiplyingArithmeticBinaryOperator<Double> implements ArithmeticBinaryOperator<Double> {
    private static final Logger logger = Logger.getLogger(MultiplyingArithmeticBinaryOperator.class);

    private final int priority;

    public MultiplyingArithmeticBinaryOperator(int priority) {
        this.priority = priority;
    }


    @Override
    public DoubleValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand){

        double result = DoubleValueReader.readDouble(leftOperand) * DoubleValueReader.readDouble(rightOperand);

        return new DoubleValueHolder(result);
    }

    /**
     *
     * @return priority of execution of adding operator (priority is 1)
     */
    public int priority() {
        return priority;
    }

}
