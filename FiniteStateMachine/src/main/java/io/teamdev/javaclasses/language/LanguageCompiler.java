package io.teamdev.javaclasses.language;

import io.teamdev.javaclasses.impl.fsm.CompileFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.IncorrectFormatOfExpressionException;
import io.teamdev.javaclasses.impl.fsm.Command;
import io.teamdev.javaclasses.impl.runtime.RuntimeEnvironment;


import java.text.StringCharacterIterator;
import java.util.ArrayList;
import java.util.List;

public class LanguageCompiler {


    public void evaluate(String mathExpression, RuntimeEnvironment environment) {

        List<Command> commands = new ArrayList<>();
        try{

        boolean isSuccess = new CompileFiniteStateMachine().run(new StringCharacterIterator(mathExpression), commands);


        if (isSuccess) {
            environment.startNewStack();

            for (Command command : commands) {
                command.execute(environment);
            }

        }
        }catch (IncorrectFormatOfExpressionException ex){
            ex.printStackTrace();
        }


    }
}
