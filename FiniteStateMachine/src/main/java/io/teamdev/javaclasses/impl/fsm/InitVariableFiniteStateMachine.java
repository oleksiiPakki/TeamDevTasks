package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class InitVariableFiniteStateMachine<T extends List<Command>> extends FiniteStateMachine<T> {

    public InitVariableFiniteStateMachine() {

        State<T> nameForInitState = new NameForVariableState<>(false, true);
        State<T> assignmentCharacterState = new AssignmentCharacterState<>(false, true, '=');
        State<T> expressionState = new BooleanExpressionState<>(true, true);

        nameForInitState.addTransition(assignmentCharacterState);
        assignmentCharacterState.addTransition(expressionState);

        setStartedStates(Collections.singleton(nameForInitState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }
}
