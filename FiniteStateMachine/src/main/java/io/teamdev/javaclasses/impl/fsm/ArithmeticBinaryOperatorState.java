package io.teamdev.javaclasses.impl.fsm;

import io.teamdev.javaclasses.impl.math.AdditionArithmeticBinaryOperator;
import io.teamdev.javaclasses.impl.math.ArithmeticBinaryOperator;
import io.teamdev.javaclasses.impl.math.DivisionArithmeticBinaryOperator;
import io.teamdev.javaclasses.impl.math.MultiplyingArithmeticBinaryOperator;
import io.teamdev.javaclasses.impl.math.SubtractionArithmeticBinaryOperator;
import org.apache.log4j.Logger;

import java.text.CharacterIterator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Implementation of State.
 * Fsm being on this  state when it finds one of the four arithmetical operations (+,-,*,/)
 *
 * @param <T>
 */
public class ArithmeticBinaryOperatorState<T extends List<Command>> extends State<T> {

    private static final Logger logger = Logger.getLogger(ArithmeticBinaryOperatorState.class);

    private final boolean mayBeFinish;
    private final boolean isLexeme;

    private final Map<Character, ArithmeticBinaryOperator<Double>> operators = new HashMap<>();

    ArithmeticBinaryOperatorState(boolean mayBeFinish, boolean isLexeme) {
        this.mayBeFinish = mayBeFinish;
        this.isLexeme = isLexeme;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean isLexeme() {
        return isLexeme;
    }

    {
        operators.put('+', new AdditionArithmeticBinaryOperator<>(2));
        operators.put('-', new SubtractionArithmeticBinaryOperator<>(2));
        operators.put('*', new MultiplyingArithmeticBinaryOperator<>(3));
        operators.put('/', new DivisionArithmeticBinaryOperator<>(3));

    }

    /**
     * Define arithmetic operator and push it on shunting yard if we have the last one
     *
     * @param inputSequence
     *         String, contains math expression
     * @param outputSequence
     *         Shunting yard to pushing binary operator we defined
     * @return true if we have defined binary operator or false if we have not
     */
    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {

        Character currentCharacter = inputSequence.current();

        Optional<ArithmeticBinaryOperator<Double>> currentOperator = defineBinaryOperator(
                currentCharacter);

        if (currentOperator.isPresent()) {
            if (logger.isInfoEnabled()) {
                logger.info("Fsm in a BinaryOperatorState: " + inputSequence.current() + "\n");
            }
            outputSequence.add(environment -> environment.topStack()
                                                         .pushOperator(currentOperator.get()));

            inputSequence.next();

            return true;
        }

        return false;
    }

    /**
     * @return whether binary operator may be finish of math expression or not
     */

    private Optional<ArithmeticBinaryOperator<Double>> defineBinaryOperator(
            Character currentCharacter) {

        return operators.containsKey(currentCharacter) ? Optional.of(
                operators.get(currentCharacter))
                                                       : Optional.empty();
    }
}
