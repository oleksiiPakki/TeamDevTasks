package io.teamdev.javaclasses.impl.abstractfactory;

import io.teamdev.javaclasses.impl.fsm.BooleanExpressionFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.CalculableFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.ExpressionFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.ExpressionWithBracketsFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.FunctionFiniteStateMachine;
import io.teamdev.javaclasses.impl.fsm.NumberFiniteStateMachine;

import java.util.EnumMap;

public class FSMFactoryImpl implements FSMFactory {
    private final EnumMap<TypeFSM, Acceptor> machines = new EnumMap<>(TypeFSM.class);

    public FSMFactoryImpl(){
        machines.put(TypeFSM.NUMBER, inputSequence -> {
            NumberFiniteStateMachine fsm = new NumberFiniteStateMachine();

            return fsm.number(inputSequence);
        });

        machines.put(TypeFSM.EXPRESSION, inputSequence -> {
            ExpressionFiniteStateMachine fsm = new ExpressionFiniteStateMachine();

            return fsm.expression(inputSequence);
        });

        machines.put(TypeFSM.EXPRESSION_WITH_BRACKETS, inputSequence ->{
            ExpressionWithBracketsFiniteStateMachine fsm = new ExpressionWithBracketsFiniteStateMachine();

            return fsm.expressionWithBrackets(inputSequence);
        });

        machines.put(TypeFSM.FUNCTION, inputSequence -> {
            FunctionFiniteStateMachine fsm = new FunctionFiniteStateMachine();

            return fsm.function(inputSequence);
        });

        machines.put(TypeFSM.CALCULABLE, inputSequence -> {
            CalculableFiniteStateMachine fsm = new CalculableFiniteStateMachine();

            return fsm.calculable(inputSequence);
        });

        machines.put(TypeFSM.BOOLEAN_EXPRESSION, inputSequence -> {
            BooleanExpressionFiniteStateMachine fsm = new BooleanExpressionFiniteStateMachine();

            return fsm.booleanExpression(inputSequence);
        });

    }

    @Override
    public Acceptor create(TypeFSM typeFSM) {
        return machines.get(typeFSM);
    }
}
