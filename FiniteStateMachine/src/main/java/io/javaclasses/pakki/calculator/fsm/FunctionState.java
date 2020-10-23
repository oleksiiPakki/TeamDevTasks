package io.javaclasses.pakki.calculator.fsm;

import io.javaclasses.pakki.calculator.math.ShuntingYard;

import java.text.CharacterIterator;

public class FunctionState<T extends ShuntingYard> extends State<T>{
    private final boolean mayBeFinish;

    public FunctionState(boolean mayBeFinish) {
        this.mayBeFinish = mayBeFinish;
    }

    @Override
    public boolean mayBeFinish() {
        return mayBeFinish;
    }

    @Override
    public boolean accept(CharacterIterator inputSequence, T outputSequence) {
        throw new NotImplementedYetExceptions();
    }
}
