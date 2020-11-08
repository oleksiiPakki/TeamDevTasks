package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstractfactory.FSMFactory;
import io.teamdev.javaclasses.impl.abstractfactory.FSMFactoryImpl;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

public class ExpressionWithBracketsState extends State<List<Command>> {

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    ExpressionWithBracketsState(boolean mayBeFinish, boolean isLexeme) {
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

        try {

            Optional<List<Command>> possibleCommands = factory.create(
                    FSMFactory.TypeFSM.EXPRESSION_WITH_BRACKETS)
                                                              .execute(inputSequence);

            boolean isSuccess = possibleCommands.isPresent();

            if (isSuccess) {
                outputSequence.add((environment) -> {

                    for (Command command : possibleCommands.get()) {
                        command.execute(environment);
                    }

                    Optional<ValueHolder> possibleResult = environment.topStack()
                                                                      .getResult();

                    if (possibleResult.isPresent()) {

                        ValueHolder resultHolder = possibleResult.get();

                        environment.topStack()
                                   .pushOperand(resultHolder);
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
