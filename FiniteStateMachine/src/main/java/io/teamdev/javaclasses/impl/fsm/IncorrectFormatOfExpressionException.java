package io.teamdev.javaclasses.impl.fsm;

/**
 * Class, represented the exception, may be thrown in cases of incorrect format of math expression, such as :
 *      *                                                                    -missing operands of binary operators;
 *      *                                                                    -empty brackets;
 *      *                                                                    -incorrect brackets sequence;
 *      *                                                                    -empty math expression;
 *      *
 *
 */
public class IncorrectFormatOfExpressionException extends Exception {
    private int position;

    public IncorrectFormatOfExpressionException(String cause, int position){
        super(cause);
        this.position = position;

    }

    public IncorrectFormatOfExpressionException(String message) {
        super(message);
    }

    public int errorPosition() {
        return position;
    }
}
