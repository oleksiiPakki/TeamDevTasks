package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.math.FunctionStructure;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class ArgumentState<T extends FunctionStructure> extends State<T> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    ArgumentState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {
        try {
            List<Command> commands = new ArrayList<>();

            boolean isSuccess = new BooleanExpressionFiniteStateMachine().run(inputSequence,
                                                                                commands);

            if (isSuccess) {
                outputSequence.addEvaluatingArgument(environment -> {
                    environment.startNewStack();

                    for (Command command : commands) {
                        command.execute(environment);
                    }
                });

            }

            return isSuccess;

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return false;
    }
}
