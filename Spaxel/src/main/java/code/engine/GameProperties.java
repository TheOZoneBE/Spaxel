package code.engine;

import code.math.VectorF;

/**
 * Created by theo on 24/06/17.
 */
public class GameProperties {
    private int score;
    private int gameTime;
    private boolean debug;
    private boolean logging;

    public int getScore() {
        return score;
    }

    public void addScore(int value){
        score += value;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getGameTime() {
        return gameTime;
    }

    public void setGameTime(int gameTime) {
        this.gameTime = gameTime;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    public boolean isLogging() {
        return logging;
    }

    public void setLogging(boolean logging) {
        this.logging = logging;
    }
}
