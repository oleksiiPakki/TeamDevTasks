package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.BooleanValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

public class NotEqualsToBinaryOperator implements BooleanBinaryOperator<Double> {

    private final int priority;

    public NotEqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand){

        boolean notEqualsTo = !(DoubleValueReader.readDouble(leftOperand).equals(DoubleValueReader.readDouble(rightOperand)));

        return new BooleanValueHolder(notEqualsTo);
    }

    @Override
    public int priority() {

        return priority;
    }
}
