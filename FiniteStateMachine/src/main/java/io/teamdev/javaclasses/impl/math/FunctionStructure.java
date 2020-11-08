package io.teamdev.javaclasses.impl.math;

import io.teamdev.javaclasses.impl.fsm.Command;
import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.runtime.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FunctionStructure {

    private final List<Command> evaluatingArguments = new ArrayList<>();
    private Function currentFunction;

    public void setFunction(Function currentFunction) {
        this.currentFunction = currentFunction;
    }

    public void addEvaluatingArgument(Command argument) {
        evaluatingArguments.add(argument);
    }

    public void execute(RuntimeEnvironment environment) throws
                                                        IncorrectFormatOfExpressionException {
        List<ValueHolder> arguments = new ArrayList<>();

        for (Command command : evaluatingArguments) {
            command.execute(environment);

            Optional<ValueHolder> possibleResult = environment.closeTopStack()
                                                              .getResult();

            if (possibleResult.isPresent()) {

                ValueHolder resultHolder = possibleResult.get();

                arguments.add(resultHolder);
            }
        }

        currentFunction.execute(environment, arguments);

    }

}
