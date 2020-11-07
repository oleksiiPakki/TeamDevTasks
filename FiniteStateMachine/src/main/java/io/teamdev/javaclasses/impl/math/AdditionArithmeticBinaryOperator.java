package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;
import org.apache.log4j.Logger;

/**
 * Class for addition two operands
 */
public class AdditionArithmeticBinaryOperator<Double> implements ArithmeticBinaryOperator<Double> {
    private static final Logger logger = Logger.getLogger(AdditionArithmeticBinaryOperator.class);

    private final int priority;

    public AdditionArithmeticBinaryOperator(int priority){
        this.priority = priority;
    }

    @Override
    public DoubleValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand){

        double result = DoubleValueReader.readDouble(leftOperand) + DoubleValueReader.readDouble(rightOperand);

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
