package io.teamdev.javaclasses.calculator;

import io.teamdev.javaclasses.impl.fsm.BooleanExpressionFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.fsm.Command;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;
import org.apache.log4j.Logger;

import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A service that enabled to compute various mathematical expressions,
 * including numbers, binary operations, brackets and functions
 * Examples:
 * 2*(5+7*(4+1))+20
 */
public class MathExpressionCalculator {

    private static final Logger logger = Logger.getLogger(MathExpressionCalculator.class);

    /**
     * @param mathExpression
     *         String, consisting of a set of numbers, binary operators and functions

     *  @throws IncorrectFormatOfExpressionException
     */
    public Optional<ValueHolder> evaluate(String mathExpression) throws
                                                                 IncorrectFormatOfExpressionException {
        if (logger.isInfoEnabled()) {
            logger.info("Evaluating started" + "\n");
        }
        List<Command> commands = new ArrayList<>();

        StringCharacterIterator inputChain = new StringCharacterIterator(mathExpression);

        boolean isSuccess = new BooleanExpressionFiniteStateMachine().run(inputChain, commands);
        boolean isEndOfInput = (inputChain.getIndex() == inputChain.getEndIndex());

        if ((isSuccess) && (isEndOfInput)) {
            RuntimeEnvironment environment = new RuntimeEnvironment();

            environment.startNewStack();

            for (Command command : commands) {
                command.execute(environment);
            }

            Optional<ValueHolder> possibleResult = environment.closeTopStack()
                                                              .getResult();

            if (possibleResult.isPresent()) {

                ValueHolder resultHolder = possibleResult.get();

                if (logger.isInfoEnabled()) {
                    logger.info("Evaluating finished successful" + "\n");
                }

                return Optional.of(resultHolder);

            }
        }

        return Optional.empty();
    }
}
