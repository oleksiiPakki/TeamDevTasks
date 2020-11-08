package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstractfactory.FSMFactory;
import io.teamdev.javaclasses.impl.abstractfactory.FSMFactoryImpl;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public class BooleanExpressionState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    BooleanExpressionState(boolean mayBeFinish, boolean isLexeme) {
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
    public boolean accept(CharacterIterator inputSequence, List<Command> outputSequence) throws
                                                                             IncorrectFormatOfExpressionException {

        FSMFactoryImpl factory = new FSMFactoryImpl();

        Optional<List<Command>> possibleCommands = factory.create(
                FSMFactory.TypeFSM.BOOLEAN_EXPRESSION)
                                                          .execute(inputSequence);
        boolean isSuccess = possibleCommands.isPresent();

        if (isSuccess) {
            outputSequence.add((environment) -> {

                environment.startNewStack();

                for (Command command : possibleCommands.get()) {
                    command.execute(environment);
                }

                Optional<ValueHolder> result = environment.closeTopStack()
                                                          .getResult();

                if (result.isPresent()) {

                    ValueHolder resultHolder = result.get();

                    environment.topStack()
                               .pushOperand(resultHolder);

                    environment.keepValue(resultHolder);
                }
            });
        }

        return isSuccess;

    }
}