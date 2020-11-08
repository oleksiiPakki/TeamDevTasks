package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class ExpressionWithBracketsFiniteStateMachine
        extends FiniteStateMachine<List<Command>> {

    public ExpressionWithBracketsFiniteStateMachine() {

        State<List<Command>> openingBracketForExpressionState = new OpeningBracketForExpressionState(
                false,
                true,
                '(');
        State<List<Command>> expressionState = new BooleanExpressionState(false, true);
        State<List<Command>> closingBracketState = new ClosingBracketState(true, true, ')');

        openingBracketForExpressionState.addTransition(expressionState);
        expressionState.addTransition(closingBracketState);

        setStartedStates(Collections.singleton(openingBracketForExpressionState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return expressionWithBrackets(inputSequence);
    }

    public Optional<List<Command>> expressionWithBrackets(CharacterIterator inputSequence) {

        try {
            List<Command> commands = new ArrayList<>();

            boolean isSuccess = run(inputSequence, commands);

            if (isSuccess) {
                return Optional.of(commands);
            }

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return Optional.empty();
    }
}
