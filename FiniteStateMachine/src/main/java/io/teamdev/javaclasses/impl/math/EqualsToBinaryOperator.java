package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.BooleanValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

public class EqualsToBinaryOperator implements BooleanBinaryOperator<Double> {
    private final int priority;

    public EqualsToBinaryOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public BooleanValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand){

        boolean equalsTo = DoubleValueReader.readDouble(leftOperand).equals(DoubleValueReader.readDouble(rightOperand));

        return new BooleanValueHolder(equalsTo);
    }

    @Override
    public int priority() {
        return priority;
    }

}
