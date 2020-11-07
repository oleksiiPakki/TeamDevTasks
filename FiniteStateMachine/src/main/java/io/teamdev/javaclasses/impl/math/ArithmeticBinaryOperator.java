package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

public interface ArithmeticBinaryOperator<Double> extends BinaryOperator<Double> {

    @Override
    DoubleValueHolder execute(ValueHolder<Double> leftOperand, ValueHolder<Double> rightOperand ) ;

}
