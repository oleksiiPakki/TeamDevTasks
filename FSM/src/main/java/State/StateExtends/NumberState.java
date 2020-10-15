package State.StateExtends;

import State.State;

public abstract class NumberState extends State {
    StringBuilder resultString = new StringBuilder("");

    @Override
    public abstract void actionInCurrentPosition(Character currentSymbol);

    @Override
    public abstract void changeState(Character nextSymbol);

    public StringBuilder getResultString() {
        return resultString;
    }

    public void setResultString(StringBuilder resultString) {
        this.resultString = resultString;
    }
}
