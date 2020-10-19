package math;

public class SubtractionOperator implements BinaryOperator {
    private int priority;

    public SubtractionOperator(int priority) {
        this.priority = priority;
    }

    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand - rightOperand;
    }

    public int priority() {
        return priority;
    }
}
