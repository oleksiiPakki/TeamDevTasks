package io.teamdev.javaclasses.impl.fsm;

import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class NameFiniteStateMachine extends FiniteStateMachine<StringBuilder> {
    private static final Logger logger = Logger.getLogger(NameFiniteStateMachine.class);

    public NameFiniteStateMachine(){
        if (logger.isInfoEnabled()){
            logger.info("Name finite machine is started\n");
        }

        State<StringBuilder> alphabeticCharacterState = new AlphabeticCharacterState(true, false);
        State<StringBuilder> digitCharacterState = new DigitCharacterState(true, false);

        alphabeticCharacterState.addTransition(alphabeticCharacterState);
        alphabeticCharacterState.addTransition(digitCharacterState);

        digitCharacterState.addTransition(digitCharacterState);

        setStartedStates(Collections.singleton(alphabeticCharacterState));
    }

    @Override
    public Optional<List<Command>> execute(CharacterIterator inputSequence) {
        return Optional.empty();
    }

}
