package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.util.*;

public class MaxFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) throws
                                                                                     WrongCountOfArgumentsException {

        Collection<Double> argumentsOfFunction = new ArrayList<>();

        if (arguments.isEmpty()) {
            throw new WrongCountOfArgumentsException(
                    "Max function must contain at least one argument");
        }

        for (ValueHolder argument : arguments) {
            argumentsOfFunction.add(DoubleValueReader.readDouble(argument));
        }

        environment.topStack()
                   .pushOperand(new DoubleValueHolder(Collections.max(argumentsOfFunction)));
    }

}
