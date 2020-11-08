package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.util.List;

public interface Function {

    void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) throws
                                                                              WrongCountOfArgumentsException;
}
