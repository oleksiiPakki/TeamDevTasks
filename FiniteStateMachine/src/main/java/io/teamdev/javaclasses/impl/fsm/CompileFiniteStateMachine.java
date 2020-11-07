package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CompileFiniteStateMachine<T extends List<Command>> extends FiniteStateMachine<T> {

    public CompileFiniteStateMachine() {

        State<T> statementState = new StatementState<>(false, true);
        State<T> semicolon = new TransitState<>(true, true, ';');

        statementState.addTransition(semicolon);
        semicolon.addTransition(statementState);

        setStartedStates(Collections.singletonList(statementState));

    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
