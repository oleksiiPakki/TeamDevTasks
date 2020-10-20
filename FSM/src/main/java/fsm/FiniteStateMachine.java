package fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Arrays.asList;
import static java.util.Arrays.fill;
import static java.util.Optional.empty;

public abstract class FiniteStateMachine<T> {

    private enum StatusOfExecution {
        FINISHED,
        DEADLOCK,
        NOT_STARTED
    }

    private final Collection<State> startedStates = new ArrayList<>();

    private StatusOfExecution currentStatus;

    protected void setStartedStates(State... states) {
        startedStates.addAll(asList(states));
    }

    public void run(CharacterIterator inputSequence, T outputSequence) throws IncorrectFormatOfExpressionException {

        Optional<State> currentState = empty();

        while (true) {

            Collection<State> transitions = defineTransitions(currentState);

            Optional<State> nextState = stepForward(inputSequence, outputSequence, transitions);
            currentStatus = defineStatus(currentState);

            if (!nextState.isPresent()) {
                if (currentStatus.equals(StatusOfExecution.FINISHED)) {
                    return ;

                } else{
                    throw new IncorrectFormatOfExpressionException("Incorrect format of mathematical expression,"
                            + " please try again");
                }
            }

            currentState = nextState;

        }
    }


    abstract double resultOfExecution(CharacterIterator inputSequence, T outputSequence) throws IncorrectFormatOfExpressionException;


    private Collection<State> defineTransitions(Optional<State> currentState) {
        return currentState.isPresent() ? currentState.get().transitions() : startedStates;
    }

    private Optional<State> stepForward(CharacterIterator inputSequence, T outputSequence, Collection<State> transitions) {

        for (State state : transitions) {
            if (state.accept(inputSequence, outputSequence)) {
                return Optional.of(state);
            }
        }

        return Optional.empty();
    }

    private StatusOfExecution defineStatus(Optional<State> currentState) {
        return currentState.map(state -> state.mayBeFinish() ? StatusOfExecution.FINISHED : StatusOfExecution.DEADLOCK)
                .orElse(StatusOfExecution.NOT_STARTED);
    }


}
