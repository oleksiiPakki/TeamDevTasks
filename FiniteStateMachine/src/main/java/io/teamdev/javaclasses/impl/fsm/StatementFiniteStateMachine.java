package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class StatementFiniteStateMachine<T extends List<Command>> extends FiniteStateMachine<T> {

    StatementFiniteStateMachine() {
        State<T> initVariableState = new InitVariableState<>(true, true);
        State<T> procedureState = new FunctionState<>(true, true);

        setStartedStates(Arrays.asList(initVariableState, procedureState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
