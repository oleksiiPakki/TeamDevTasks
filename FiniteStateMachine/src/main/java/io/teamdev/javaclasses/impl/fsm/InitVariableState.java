package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class InitVariableState extends State<List<Command>> {
    private final boolean mayBeFinish;
    private final boolean isLexeme;

    InitVariableState(boolean mayBeFinish, boolean isLexeme) {
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
        int currentPosition = inputSequence.getIndex();

        try{

            List<Command> commands = new ArrayList<>();

            boolean isSuccess = new InitVariableFiniteStateMachine().run(inputSequence, commands);

            if (isSuccess){

                outputSequence.add(environment -> {

                    for (Command command : commands){

                        command.execute(environment);
                    }

                    environment.initialize();
                });
            }

            return isSuccess;

        }catch (IncorrectFormatOfExpressionException ex){
            inputSequence.setIndex(currentPosition);
            ex.getCause();
        }
        return false;
    }
}
