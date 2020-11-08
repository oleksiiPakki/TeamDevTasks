package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.BooleanValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

public class LessThanOrEqualsToBinaryOperator implements BooleanBinaryOperator<Double> {
    private final int priority;

    public LessThanOrEqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand){

        boolean lessThanOrEqualsTo = DoubleValueReader.readDouble(leftOperand) <= DoubleValueReader.readDouble(rightOperand);

        return new BooleanValueHolder(lessThanOrEqualsTo);
    }

    @Override
    public int priority() {
        return priority;
    }
}
