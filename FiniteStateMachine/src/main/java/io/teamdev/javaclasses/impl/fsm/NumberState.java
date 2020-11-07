package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.abstractfactory.FSMFactory;
import io.teamdev.javaclasses.impl.abstractfactory.FSMFactoryImpl;
import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of State.
 * Fsm being on this state when it finds a number, represented by string
 *
 * @param <T>
 *         define the class, representing the result of execution on this state
 */
public class NumberState<T extends List<Command>> extends State<T> {

    private static final Logger logger = Logger.getLogger(NumberState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    NumberState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    /**
     * @return whether state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    /**
     * Define a single number in math expression, and push it into shunting yard
     *
     * @param inputSequence
     *         String, contains math expression
     * @param outputSequence
     *         Shunting yard with the result of being fsm on this state
     * @return Whether fsm being on this state or not
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {

        FSMFactoryImpl factory = new FSMFactoryImpl();

        try {

            Optional<List<Command>> possibleCommands = factory.create(FSMFactory.TypeFSM.NUMBER)
                                                              .execute(inputSequence);

            if (possibleCommands.isPresent()) {

                List<Command> commands = possibleCommands.get();

                outputSequence.addAll(commands);

                return true;
            }

            return false;

        } catch (IncorrectFormatOfExpressionException ex) {
            ex.getCause();
        }

        return false;

    }
}

