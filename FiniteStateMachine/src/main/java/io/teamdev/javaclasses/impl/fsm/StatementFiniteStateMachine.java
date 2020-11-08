package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StatementFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    StatementFiniteStateMachine() {
        State<List<Command>> initVariableState = new InitVariableState(true, true);
        State<List<Command>> procedureState = new FunctionState(true, true);

        setStartedStates(Arrays.asList(initVariableState, procedureState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
