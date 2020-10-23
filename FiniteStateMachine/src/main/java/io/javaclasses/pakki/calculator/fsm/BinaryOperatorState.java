package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.*;
import org.apache.log4j.Logger;


import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
/**Implementation of State.
 * Fsm being on this state when it finds one of the four arithmetical operations (+,-,*,/)
 *
 * @param <T> define the class, representing the result of execution on this state
 */
public class BinaryOperatorState<T extends ShuntingYard> extends State<T> {
    private static final Logger logger = Logger.getLogger(BinaryOperatorState.class);


    private final boolean mayBeFinish;

    private final Map<Character, BinaryOperator> operators = new HashMap<>();

    public BinaryOperatorState(boolean mayBeFinish){
        this.mayBeFinish = mayBeFinish;
    }

    {
        operators.put('+', new AdditionOperator(1));
        operators.put('-', new SubtractionOperator(1));
        operators.put('*', new MultiplyingOperator(2));
        operators.put('/', new DivisionOperator(2));

    }

    /**Define arithmetic operator and push it on shunting yard
     *@param inputSequenceOfCharacter String, contains math expression
     *@param outputSequence Shunting yard with the result of being fsm on this state
     * @return Whether fsm may being on this state
     */
    @Override
    public boolean accept(CharacterIterator inputSequenceOfCharacter, T outputSequence) {

        Character currentCharacter = inputSequenceOfCharacter.current();

        Optional<BinaryOperator> currentOperator = defineBinaryOperator(currentCharacter);

        if (currentOperator.isPresent()) {
            if (logger.isTraceEnabled()){
                logger.trace("Fsm in a BinaryOperatorState: " + inputSequenceOfCharacter.current() + "\n");
            }
            outputSequence.pushOperator(currentOperator.get());

            inputSequenceOfCharacter.next();

            return true;
        }

        return false;
    }

    /**
     * @return whether state may be finish or not
     */
    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    private Optional<BinaryOperator> defineBinaryOperator(Character currentCharacter) {

        return operators.containsKey(currentCharacter) ? Optional.of(operators.get(currentCharacter))
                : Optional.empty();
    }
}
