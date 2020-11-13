package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.util.List;

public class SumFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) {
        int sum = 0;

        for (ValueHolder argument : arguments){

            sum += DoubleValueReader.readDouble(argument);
        }

        environment.topStack().pushOperand(new DoubleValueHolder(sum));
    }
}
