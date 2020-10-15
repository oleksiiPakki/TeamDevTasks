package State.StateExtends.NumberStateExtends;

import State.StateExtends.NumberState;

public class DecimalNumberState extends NumberState {
    @Override
    public void actionInCurrentPosition(Character currentSymbol) {
        setResultString(getResultString().append(currentSymbol));
    }

    @Override
    public void changeState(Character nextSymbol) {
        if (((int)nextSymbol >= 48) && ((int)nextSymbol <= 57)){
            setNextState(new DecimalNumberState());
        } else {
            setNextState(new FinishStateNumber());
        }
    }
}
