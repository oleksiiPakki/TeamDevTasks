package fsm;

import java.text.CharacterIterator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class State<T> {

    public abstract boolean mayBeFinish();

    private List<State> transitions = new ArrayList<>();


    public void addTransition(State state){
        transitions.add(state);
    }

    public List<State> transitions() {
        return Collections.unmodifiableList(transitions);
    }

    public abstract boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequenceOfCharacter);
}
