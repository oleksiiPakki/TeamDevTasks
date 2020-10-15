package State.StateExtends.NumberStateExtends;

import State.StateExtends.NumberState;


public class IntegerNumberState extends NumberState {
    @Override
    public void actionInCurrentPosition(Character currentSymbol) {
        setResultString(getResultString().append(currentSymbol));
    }

    @Override
    public void changeState(Character nextSymbol) {
        if (((int) nextSymbol >= 48) && ((int) nextSymbol <= 57)) {
            setNextState(new IntegerNumberState());
        } else if (nextSymbol.equals('.')) {
            setNextState(new PointState());
        } else {
            setNextState(new FinishStateNumber());
        }
    }
}


