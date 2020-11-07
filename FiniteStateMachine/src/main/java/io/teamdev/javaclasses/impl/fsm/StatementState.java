package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class StatementState<T extends List<Command>> extends State<T> {

    public final boolean mayBeFinish;
    private final boolean isLexeme;

    public StatementState(boolean mayBeFinish, boolean isLexeme) {
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

            boolean isSuccess = new StatementFiniteStateMachine<>().run(inputSequence, commands);

            outputSequence.add(environment -> {

                for (Command command : commands) {
                    command.execute(environment);
                }
            });

            return isSuccess;

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return false;
    }
}
