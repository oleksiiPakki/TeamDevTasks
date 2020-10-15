package State;

import State.StateExtends.NumberState;

public abstract class State {
   private NumberState nextState = null;

   abstract public void actionInCurrentPosition(Character currentSymbol);

   abstract public void changeState(Character nextSymbol);

   public void setNextState(NumberState nextState){
      this.nextState = nextState;
   }

   public NumberState getNextState() {
      return nextState;
   }
}
