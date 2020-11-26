package io.teamdev.javaclasses.calculator;

import io.teamdev.javaclasses.impl.abstracts.FSMFactory;
import io.teamdev.javaclasses.impl.abstracts.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.fsm.FSMFactoryImpl;
import io.teamdev.javaclasses.impl.runtime.Command;
import io.teamdev.javaclasses.impl.runtime.ProgramExecutionException;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;
import io.teamdev.javaclasses.impl.runtime.ValueHolder;

import java.text.StringCharacterIterator;
import java.util.List;
import java.util.Optional;

/**
 * A service that enabled to compute various mathematical expressions,
 * including numbers, binary operations, brackets and functions
 * Examples:
 * 2*(5+7*(4+1))+20
 */
public class MathExpressionCalculator {


    /**
     * @param mathExpression String, consisting of a set of numbers, binary operators and functions
     * @throws IncorrectFormatOfExpressionException throws when we have mistakes in expression, such a:
     *                                              -Binary operators, following to each others
     *                                              -Incorrect brackets sequence
     *                                              -
     */
    public String evaluate(String mathExpression) throws
            IncorrectFormatOfExpressionException, ProgramExecutionException {


        StringCharacterIterator inputChain = new StringCharacterIterator(mathExpression);

        FSMFactoryImpl factory = new FSMFactoryImpl();

        Optional<List<Command>> commands = factory.create(FSMFactory.TypeFSM.BOOLEAN_EXPRESSION).execute(inputChain);

        if (commands.isPresent()) {
            boolean isEndOfInput = (inputChain.getIndex() == inputChain.getEndIndex());

            if (isEndOfInput) {
                RuntimeEnvironment environment = new RuntimeEnvironment();

                environment.startNewStack();

                for (Command command : commands.get()) {
                    command.execute(environment);
                }

                ValueHolder resultHolder = environment.closeTopStack()
                        .getResult();



                return resultHolder.toString();

            }


        }

        throw new IncorrectFormatOfExpressionException("Deadlock on " + inputChain.getIndex() + " position");
    }
}



