package io.javaclasses.pakki.calculator.math;

public interface BinaryOperator {
    double execute(double leftOperand, double rightOperand);
    int priority();
}
