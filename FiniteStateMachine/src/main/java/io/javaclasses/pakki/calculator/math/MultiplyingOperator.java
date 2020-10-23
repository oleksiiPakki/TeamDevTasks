package io.javaclasses.pakki.calculator.math;

public class MultiplyingOperator implements BinaryOperator {
    private final int priority;

    public MultiplyingOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand * rightOperand;
    }

    public int priority() {
        return priority;
    }

}
