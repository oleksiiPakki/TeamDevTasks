package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.ShuntingYard;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;

/**Implementation of State.
 * Fsm being on this state when it finds a number, represented by string
 *
 * @param <T> define the class, representing the result of execution on this state
 */
public class NumberState<T extends ShuntingYard> extends State<T> {
    private static final Logger logger = Logger.getLogger(NumberState.class);

    private final boolean mayBeFinish;

    public NumberState(boolean mayBeFinish) {
        this.mayBeFinish = mayBeFinish;
    }

    /**
     * @return whether state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    /**Define a single number in math expression, and push it into shunting yard
     *
     * @param inputSequenceOfCharacter String, contains math expression
     * @param outputSequence Shunting yard with the result of being fsm on this state
     * @return Whether fsm being on this state or not
     */
    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequence) {

        try {
            if (logger.isTraceEnabled()){
                logger.trace("Fsm in a NumberState: " + inputSequenceOfCharacter.current() + "\n");
            }
            StringBuilder currentPartOfExpression = new StringBuilder();

            new NumberFiniteStateMachine<>().run(inputSequenceOfCharacter, currentPartOfExpression);

            double resultNumber = Double.parseDouble(currentPartOfExpression.toString());

            outputSequence.pushOperand(resultNumber);

            return true;

        } catch (IncorrectFormatOfExpressionException ex) {
            logger.error("Wrong format of math expression, deadlock on: "
                    + inputSequenceOfCharacter.previous());
        }

        return false;
    }

}

