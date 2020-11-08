package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InitVariableFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public InitVariableFiniteStateMachine() {

        State<List<Command>> nameForInitState = new NameForVariableState(false, true);
        State<List<Command>> assignmentCharacterState = new AssignmentCharacterState(false, true, '=');
        State<List<Command>> expressionState = new BooleanExpressionState(true, true);

        nameForInitState.addTransition(assignmentCharacterState);
        assignmentCharacterState.addTransition(expressionState);

        setStartedStates(Collections.singleton(nameForInitState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
