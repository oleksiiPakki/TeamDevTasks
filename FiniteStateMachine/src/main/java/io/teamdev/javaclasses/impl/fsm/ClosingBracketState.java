package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.runtime.ShuntingYard;
import io.teamdev.javaclasses.impl.runtime.DoubleValueHolder;
import io.teamdev.javaclasses.impl.runtime.DoubleValueReader;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link State}, fsm being in when we finding closed bracket after parameters of
 * function
 *
 * @param <T>
 *         Shunting yard{@link  ShuntingYard} contains intermediate result of execution of binary
 *         operations,
 *         considering priority of last ones
 */
public class ClosingBracketState<T extends List<Command>> extends State<T> {

    private static final Logger logger = Logger.getLogger(ClosingBracketState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;
    private final Character requiredCharacter;

    ClosingBracketState(boolean mayBeFinish, boolean isLexeme, Character requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
        this.requiredCharacter = requiredCharacter;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    /**
     * @return whether closing bracket may be finish state or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    /**
     * pushing closing bracket into a Shunting yard {@link ShuntingYard}, if current character is
     * ')'
     *
     * @param inputSequence
     *         String, contains math expression
     * @param outputSequence
     *         Shunting Yard,{@link ShuntingYard} consisting arguments of functions
     * @return true if current character is ')' or false if it is not
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {

        Character currentCharacter = inputSequence.current();

        if (requiredCharacter.equals(currentCharacter)) {
            if (logger.isInfoEnabled()) {
                logger.info("fsm in a closing bracket for function state");
            }

            outputSequence.add(environment -> {

                environment.topStack()
                           .pushClosingBracket();

                Optional<ValueHolder> possibleResult = environment.closeTopStack()
                                                                  .getResult();

                if (possibleResult.isPresent()) {

                    double result = DoubleValueReader.readDouble(possibleResult.get());

                    environment.topStack()
                               .pushOperand(new DoubleValueHolder(result));
                }
            });

            inputSequence.next();

            return true;
        }

        return false;
    }
}
