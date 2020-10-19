package math;

public interface BinaryOperator {
    double execute(double leftOperand, double rightOperand);
    int priority();
}
