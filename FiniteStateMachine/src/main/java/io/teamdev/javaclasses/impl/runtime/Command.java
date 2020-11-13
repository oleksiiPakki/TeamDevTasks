package io.teamdev.javaclasses.impl.abstracts;

import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

public interface Command {
    void execute(RuntimeEnvironment environment) throws DeadLockException, ProgramExecutionException;

}
