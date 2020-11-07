package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstractfactory.FSMFactory;
import io.teamdev.javaclasses.impl.abstractfactory.FSMFactoryImpl;
import io.teamdev.javaclasses.impl.math.FunctionStructure;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;

import java.text.CharacterIterator;
import java.util.*;

public class FunctionState<T extends List<Command>> extends State<T> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    FunctionState(boolean mayBeFinish, boolean isLexeme) {
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

        FSMFactoryImpl factory = new FSMFactoryImpl();
        try {

            Optional<List<Command>> possibleCommands = factory.create(
                    FSMFactory.TypeFSM.FUNCTION)
                                                              .execute(inputSequence);

            boolean isSuccess = possibleCommands.isPresent();

            if (isSuccess) {
                outputSequence.add(new Command() {
                    @Override
                    public void execute(RuntimeEnvironment environment) throws
                                                                        IncorrectFormatOfExpressionException {
                        for (Command command : possibleCommands.get()) {
                            command.execute(environment);
                        }
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
