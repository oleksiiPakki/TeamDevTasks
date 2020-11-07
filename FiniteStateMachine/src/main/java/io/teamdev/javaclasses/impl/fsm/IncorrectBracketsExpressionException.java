package io.teamdev.javaclasses.impl.fsm;

public class IncorrectBracketsExpressionException extends IncorrectFormatOfExpressionException {
    private  int position;

    public IncorrectBracketsExpressionException(String message){
        super(message);
    }

    public IncorrectBracketsExpressionException(String message, int position) {
        super(message,position);

        this.position = position;
    }
}
