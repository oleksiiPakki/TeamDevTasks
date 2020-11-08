package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.ValueHolder;

public interface BinaryOperator<T> {
    ValueHolder execute(ValueHolder<T> leftOperand, ValueHolder<T> rightOperand);

    int priority();
}
