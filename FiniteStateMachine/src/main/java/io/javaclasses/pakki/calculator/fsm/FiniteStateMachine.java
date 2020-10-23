package io.javaclasses.pakki.calculator.fsm;


import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import static java.util.Optional.empty;

/**
 * @param <T> define the class, representing the result of execution
 */
public abstract class FiniteStateMachine<T> {

    private Collection<State<T>> transitions = new ArrayList<>();

    /**Registering possible start states for fsm
     *
     * @param states List of possible start states
     */
    protected void setStartedStates(Iterable<State<T>> states) {
        for (State<T> state : states) {
            transitions.add(state);
        }
    }

    /**Execution of fsm
     *
     * @param inputSequence String, contains math expression
     * @param outputSequence result of execution of fsm
     * @throws IncorrectFormatOfExpressionException in cases of incorrect format of math expression, such as :
     *                                                                    -missing operands of binary operators;
     *                                                                    -empty brackets;
     *                                                                    -incorrect brackets sequence;
     *                                                                    -empty math expression;
     *
     */
    public void run(CharacterIterator inputSequence, T outputSequence)
            throws IncorrectFormatOfExpressionException {

        Optional<State<T>> currentState = empty();

        while (true) {
            //if current state is present - define next possible states, else transitions is started states
            currentState.ifPresent(tState -> transitions = tState.transitions());

            //get next possible state after current
            Optional<State<T>> nextState = stepForward(inputSequence, outputSequence, transitions);

            if (!nextState.isPresent()) {

                //if we can't step forward and current state may be finish, we finishing execution of fsm
                if (currentState.isPresent() && currentState.get().mayBeFinish()){
                    return ;
                }
                //if we can't step forward and current state may be not finish, we throw exception
                throw new IncorrectFormatOfExpressionException("You have incorrect format of expression");
            }

            currentState = nextState;
        }

    }

    /**
     *
     * @param inputSequence String, contains math expression
     * @param outputSequence Result of execution of fsm on current state
     * @param transitions possible transitions for current state
     * @return next state
     */
    private Optional<State<T>> stepForward(CharacterIterator inputSequence, T outputSequence, Collection<State<T>> transitions) {

        for (State<T> state : transitions) {
            if (state.accept(inputSequence, outputSequence)) {
                return Optional.of(state);
            }
        }

        return Optional.empty();
    }


}
