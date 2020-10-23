package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.ShuntingYard;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.text.StringCharacterIterator;

/**
 * Implementation of State.
 * Fsm being on this state when it finds opened bracket
 *
 * @param <T> define the class, representing the result of execution on this state
 */
public class BracketsState<T extends ShuntingYard> extends State<T> {
    private static final Logger logger = Logger.getLogger(BracketsState.class);

    private final boolean mayBeFinish;
    private final char requiredCharacter;

    public BracketsState(boolean mayBeFinish, char requiredCharacter) {
        this.mayBeFinish = mayBeFinish;
        this.requiredCharacter = requiredCharacter;
    }

    /**
     * @return whether state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    /**
     * Define math expression in brackets, calculate the last one and push it on shunting yard
     *
     * @param inputSequenceOfCharacter String, contains math expression
     * @param outputSequence           Shunting yard with the result of being fsm on this state
     * @return Whether fsm being on this state or not
     */
    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequence) {

        ShuntingYard yardForExpressionInBrackets = new ShuntingYard();
        Character currentCharacter = inputSequenceOfCharacter.current();

        try {
            if (currentCharacter.equals(requiredCharacter)) {
                if (inputSequenceOfCharacter.next() == ')') {
                    throw new IncorrectFormatOfExpressionException("Empty brackets");
                }

                if (logger.isTraceEnabled()) {
                    logger.trace("Fsm in a BracketState: " + inputSequenceOfCharacter.current() + "\n");
                }

                String expressionInBrackets = expressionInBrackets(inputSequenceOfCharacter);

                if (logger.isTraceEnabled()) {
                    logger.trace("An expression in brackets " + expressionInBrackets + "\n");
                }

                //calculated math expression in brackets
                new ExpressionFiniteStateMachine<>().run(new StringCharacterIterator(expressionInBrackets),
                        yardForExpressionInBrackets);

                double resultOfExpressionInBrackets = yardForExpressionInBrackets.popAllOperators();

                if (logger.isTraceEnabled()) {
                    logger.trace("The result of calculation expression in brackets is " + resultOfExpressionInBrackets + "\n");
                }

                //push the result in brackets into shunting yard
                outputSequence.pushOperand(resultOfExpressionInBrackets);

                return true;
            }
        } catch (IncorrectFormatOfExpressionException ex) {
            logger.error("Wrong format of math expression, deadlock on: "
                    + inputSequenceOfCharacter.previous(), ex.getCause());
        }

        return false;
    }


    private String expressionInBrackets(CharacterIterator inputSequenceOfCharacter) {
        StringBuilder expressionInBrackets = new StringBuilder();

        int indexToDefineCorrespondingClosedBracket = 1;

        //appending all symbols while we didn't find closed bracket corresponding to opened one
        while (indexToDefineCorrespondingClosedBracket > 0) {

            //appending all symbols between brackets
            expressionInBrackets.append(inputSequenceOfCharacter.current());
            inputSequenceOfCharacter.next();

            if (inputSequenceOfCharacter.current() == '(') {

                indexToDefineCorrespondingClosedBracket++;

            } else if (inputSequenceOfCharacter.current() == ')') {

                indexToDefineCorrespondingClosedBracket--;
            }

        }

        //needed to set current character to character, following after closed bracket
        inputSequenceOfCharacter.next();

        return expressionInBrackets.toString();
    }

}
