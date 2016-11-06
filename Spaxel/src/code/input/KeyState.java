package code.input;

/**
 * Created by theod on 6-11-2016.
 */
public class KeyState {
    private boolean state;
    private boolean prevState;

    public void setState(boolean state){
        prevState = this.state;
        this.state = state;
    }

    public boolean getState(){
        return state;
    }

    public boolean getPrevState(){
        return prevState;
    }
}
