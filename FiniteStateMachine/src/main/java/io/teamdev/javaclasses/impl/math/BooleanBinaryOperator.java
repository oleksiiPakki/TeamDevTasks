package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.BooleanValueHolder;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

public interface BooleanBinaryOperator<Double> extends BinaryOperator<Double> {

    @Override
    BooleanValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand);

}
