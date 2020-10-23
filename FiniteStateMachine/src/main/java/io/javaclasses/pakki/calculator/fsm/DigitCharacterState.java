package io.javaclasses.pakki.calculator.fsm;

import org.apache.log4j.Logger;

import java.text.CharacterIterator;
/**Implementation of State.
 * Fsm being on this state when it finds a digit
 *
 * @param <T> define the class, representing the result of execution on this state
 */
public class DigitCharacterState<T extends StringBuilder> extends State<T> {
    private static final Logger logger = Logger.getLogger(DigitCharacterState.class);

    private final boolean mayBeFinish;

    public DigitCharacterState(boolean mayBeFinish){
        this.mayBeFinish = mayBeFinish;

    }

    /**
     * @return whether state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    /**Define digit and append it to result StringBuilder
     *
     * @param inputSequenceOfCharacter String, contains math expression
     * @param outputSequence StringBuilder yard with the result of being fsm on this state
     * @return Whether fsm being on this state or not
     */
    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequence) {
        char currentCharacterOfSequence = inputSequenceOfCharacter.current();

        if (Character.isDigit(currentCharacterOfSequence)){
            if (logger.isTraceEnabled()){
                logger.trace("Fsm in a DigitState: " + inputSequenceOfCharacter.current() + "\n");
            }
            outputSequence.append(currentCharacterOfSequence);
            inputSequenceOfCharacter.next();

            return true;
        }

        return false;
    }
}
