import State.State;
import State.StateExtends.NumberState;
import State.StateExtends.NumberStateExtends.StartNumberPosition;

public class Main {
    public static void main(String[] args) {
        String string = "-1234.5345wdfsdg";

        StartNumberPosition start = new StartNumberPosition();
        start.actionInCurrentPosition(string.charAt(0));
        NumberState currentState = start.getNextState();

        for (int i = 1; i < string.length(); i++){

            currentState.actionInCurrentPosition(string.charAt(i - 1));
            currentState.changeState(string.charAt(i));
            String result = currentState.getResultString().toString();
            System.out.println(result);
            currentState = currentState.getNextState();
        }
    }
}
