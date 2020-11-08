package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

public interface Command {
    void execute(RuntimeEnvironment environment) throws IncorrectFormatOfExpressionException;

}
