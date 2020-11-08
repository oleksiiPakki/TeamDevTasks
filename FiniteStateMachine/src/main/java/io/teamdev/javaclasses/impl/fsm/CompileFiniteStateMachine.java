package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class CompileFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public CompileFiniteStateMachine() {

        State<List<Command>> statementState = new StatementState(false, true);
        State<List<Command>> semicolon = new TransitState(true, true, ';');

        statementState.addTransition(semicolon);
        semicolon.addTransition(statementState);

        setStartedStates(Collections.singletonList(statementState));

    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
