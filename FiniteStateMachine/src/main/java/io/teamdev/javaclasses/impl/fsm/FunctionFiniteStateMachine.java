package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.math.FunctionStructure;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class FunctionFiniteStateMachine extends FiniteStateMachine<FunctionStructure> {

    public FunctionFiniteStateMachine() {

        State<FunctionStructure> nameState = new NameForFunctionState(false, false);
        State<FunctionStructure> openingBracketForFunctionState = new TransitState<>(false, true, '(');
        State<FunctionStructure> argumentState = new ArgumentState(false, true);
        State<FunctionStructure> commaState = new TransitState<>(false, true, ',');
        State<FunctionStructure> closingBracketState = new TransitState<>(true, true, ')');

        nameState.addTransition(openingBracketForFunctionState);

        openingBracketForFunctionState.addTransition(argumentState);
        openingBracketForFunctionState.addTransition(closingBracketState);

        argumentState.addTransition(commaState);
        argumentState.addTransition(closingBracketState);

        commaState.addTransition(argumentState);

        setStartedStates(Collections.singletonList(nameState));

    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return function(inputSequence);
    }

    public Optional<List<Command>> function(CharacterIterator inputSequence) {

        try {
            FunctionStructure functionStructure = new FunctionStructure();

            boolean isSuccess = run(inputSequence, functionStructure);

            if (isSuccess) {

                List<Command> commands = new ArrayList<>();

                commands.add(environment -> functionStructure.execute(environment));

                return Optional.of(commands);
            }

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return Optional.empty();
    }
}
