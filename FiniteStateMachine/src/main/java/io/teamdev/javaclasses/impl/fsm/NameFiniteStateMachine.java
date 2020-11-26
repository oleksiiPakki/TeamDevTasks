package io.teamdev.javaclasses.impl.fsm;

import com.sun.istack.internal.logging.Logger;
import io.teamdev.javaclasses.impl.abstracts.FiniteStateMachine;
import io.teamdev.javaclasses.impl.abstracts.State;
import io.teamdev.javaclasses.impl.runtime.Command;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.logging.LogManager;

public class NameFiniteStateMachine extends FiniteStateMachine<StringBuilder> {

    public NameFiniteStateMachine() {


        State<StringBuilder> alphabeticCharacterState = new AlphabeticCharacterState();
        State<StringBuilder> digitCharacterState = new DigitCharacterState();

        alphabeticCharacterState.addTransition(alphabeticCharacterState);
        alphabeticCharacterState.addTransition(digitCharacterState);

        digitCharacterState.addTransition(digitCharacterState);

        addStartedStates(Collections.singleton(alphabeticCharacterState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }

}
