package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.util.List;
import java.util.Optional;

public class PiFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) {
        environment.topStack()
                   .pushOperand(new DoubleValueHolder(
                           Optional.of(Math.round(Math.PI * 100.0) / 100.0)
                                   .get()));
    }

}
