package io.teamdev.javaclasses.impl.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class CalculableFiniteStateMachine extends FiniteStateMachine<List<Command>> {

    public CalculableFiniteStateMachine() {

        State<List<Command>> numberState = new NumberState<>(true, true);
        State<List<Command>> expressionWithBrackets = new ExpressionWithBracketsState<>(true, true);
        State<List<Command>> functionState = new FunctionState<>(true, true);
        State<List<Command>> variableState = new VariableState<>(true, true);

        setStartedStates(
                Arrays.asList(numberState, expressionWithBrackets, variableState, functionState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return calculable(inputSequence);
    }

    public Optional<List<Command>> calculable(CharacterIterator inputSequence) {
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
