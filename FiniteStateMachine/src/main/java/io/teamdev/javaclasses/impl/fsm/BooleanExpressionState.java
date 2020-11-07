package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstractfactory.FSMFactory;
import io.teamdev.javaclasses.impl.abstractfactory.FSMFactoryImpl;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public class BooleanExpressionState<T extends List<Command>> extends State<T> {

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
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {

        FSMFactoryImpl factory = new FSMFactoryImpl();

        try {

            Optional<List<Command>> possibleCommands = factory.create(FSMFactory.TypeFSM.BOOLEAN_EXPRESSION)
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

                        ValueHolder<Double> resultHolder = result.get();

                        environment.topStack()
                                   .pushOperand(resultHolder);

                        environment.keepValue(resultHolder);
                    }
                });
            }

            return isSuccess;

        }catch (IncorrectFormatOfExpressionException ex){
            ex.getCause();
        }

        return false;
    }
}
