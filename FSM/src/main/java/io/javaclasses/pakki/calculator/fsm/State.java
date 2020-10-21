package io.javaclasses.pakki.calculator.fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class State<T> {

    public abstract boolean mayBeFinish();

    private final List<State<T>> transitions = new ArrayList<>();


    public void addTransition(State<T> state){
        transitions.add(state);
    }

    public List<State<T>> transitions() {
        return Collections.unmodifiableList(transitions);
    }

    public abstract boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter);
}
