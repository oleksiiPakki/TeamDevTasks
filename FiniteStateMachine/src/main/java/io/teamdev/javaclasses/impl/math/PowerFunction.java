package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.util.ArrayDeque;
import java.util.List;

public class PowerFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments)
            throws WrongCountOfArgumentsException {

        int requiredCountOfArguments = 2;

        if (requiredCountOfArguments != arguments.size()) {
            throw new WrongCountOfArgumentsException(
                    "Pow function must contains only two arguments");
        }

        ArrayDeque<Double> argumentsOfFunction = new ArrayDeque<>();

        for (ValueHolder argument : arguments) {
            argumentsOfFunction.add(DoubleValueReader.readDouble(argument));
        }

        double base = argumentsOfFunction.pop();
        double exponent = argumentsOfFunction.pop();

        environment.topStack()
                   .pushOperand(new DoubleValueHolder(Math.pow(base, exponent)));
    }

}
