package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;

public class WrongCountOfArgumentsException extends IncorrectFormatOfExpressionException {
    public WrongCountOfArgumentsException(String message){
        super(message);
    }
}
