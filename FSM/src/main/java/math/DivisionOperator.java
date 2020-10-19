package math;

public class DivisionOperator implements BinaryOperator {
    private int priority;

    public DivisionOperator(int priority){
        this.priority = priority;
    }
    @Override
    public double execute(double leftOperand, double rightOperand) {
        return leftOperand / rightOperand;
    }

    public int priority(){
        return priority;
    }
}
