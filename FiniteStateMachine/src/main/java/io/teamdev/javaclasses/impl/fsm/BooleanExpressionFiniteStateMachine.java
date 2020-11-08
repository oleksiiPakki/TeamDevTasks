package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class BooleanExpressionFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public BooleanExpressionFiniteStateMachine() {

        State<List<Command>> expressionState = new ExpressionState(true, true);
        State<List<Command>> booleanOperator = new BooleanBinaryOperatorState(false, true);

        expressionState.addTransition(booleanOperator);

        booleanOperator.addTransition(expressionState);

        setStartedStates(Collections.singletonList(expressionState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) throws IncorrectFormatOfExpressionException {
        return booleanExpression(inputSequence);
    }

    public Optional<List<Command>> booleanExpression(CharacterIterator inputSequence) throws IncorrectFormatOfExpressionException {
            List<Command> commands = new ArrayList<>();

            boolean isSuccess = run(inputSequence, commands);

            if (isSuccess) {
                return Optional.of(commands);
            }


        return Optional.empty();

    }
}

