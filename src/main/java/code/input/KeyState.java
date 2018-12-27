package code.input;

/**
 * Created by theod on 6-11-2016.
 */
public class KeyState {
    private boolean down;
    private boolean wasDown;

    public KeyState() {
        super();
    }

    public void setState(boolean down) {
        wasDown = this.down;
        this.down = down;
    }

    public boolean isDown() {
        return down;
    }

    public boolean hasBeenDown() {
        return wasDown;
    }
}
