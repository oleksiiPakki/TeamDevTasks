package io.teamdev.javaclasses.impl.fsm;

/**Throws when fsm can not move forward and current character may not be finish
 *
 */
public class DeadLockException extends IncorrectFormatOfExpressionException {
    private final int position;

    public DeadLockException(String message, int position) {
        super(message, position);

        this.position = position;
    }

    public int errorPosition() {
        return position;
    }
}
