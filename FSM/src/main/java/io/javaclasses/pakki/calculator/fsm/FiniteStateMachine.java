package io.javaclasses.pakki.calculator.fsm;


import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;

import java.util.Optional;

import static java.util.Optional.empty;


public abstract class FiniteStateMachine<T> {

    private Collection<State<T>> transitions = new ArrayList<>();

    protected void setStartedStates(Iterable<State<T>> states) {
        for (State<T> state : states) {
            transitions.add(state);
        }
    }

    public void run(CharacterIterator inputSequence, T outputSequence)
            throws IncorrectFormatOfExpressionException {

        Optional<State<T>> currentState = empty();

        while (true) {
            currentState.ifPresent(tState -> transitions = tState.transitions());

            Optional<State<T>> nextState = stepForward(inputSequence, outputSequence, transitions);

            if (!nextState.isPresent()) {
                if (currentState.isPresent() && currentState.get().mayBeFinish()){
                    return ;
                }

                throw new IncorrectFormatOfExpressionException("You have incorrect format of expression - deadlock " +
                        "or not started");
            }

            currentState = nextState;
        }

    }


    private Optional<State<T>> stepForward(CharacterIterator inputSequence, T outputSequence, Collection<State<T>> transitions) {

        for (State<T> state : transitions) {
            if (state.accept(inputSequence, outputSequence)) {
                return Optional.of(state);
            }
        }

        return Optional.empty();
    }


}
