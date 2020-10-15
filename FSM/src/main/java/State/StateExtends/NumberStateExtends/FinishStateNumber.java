package State.StateExtends.NumberStateExtends;

import State.StateExtends.NumberState;

public class FinishStateNumber extends NumberState {
    @Override
    public void actionInCurrentPosition(Character currentSymbol) {
        System.out.println(Double.parseDouble(getResultString().toString()));
    }

    @Override
    public void changeState(Character nextSymbol) {

    }
}
