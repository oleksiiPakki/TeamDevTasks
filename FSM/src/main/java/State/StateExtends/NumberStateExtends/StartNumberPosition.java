package State.StateExtends.NumberStateExtends;

import State.StateExtends.NumberState;

public class StartNumberPosition extends NumberState {
    @Override
    public void actionInCurrentPosition(Character currentSymbol) {

        changeState(currentSymbol);
    }

    @Override
    public void changeState(Character nextSymbol) {
        if (((int)nextSymbol >= 48) && ((int)nextSymbol <= 57)){
            setNextState(new IntegerNumberState());
        } else if (nextSymbol.equals('-')){
            setNextState(new NegativeNumberState());
        }
    }
}
