package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.util.List;

public class PrintFunction implements Function {

    @Override
    public void execute(RuntimeEnvironment environment, List<ValueHolder> arguments) {

        for (ValueHolder argument : arguments){

            environment.output().append(argument.value());
        }
    }


}
