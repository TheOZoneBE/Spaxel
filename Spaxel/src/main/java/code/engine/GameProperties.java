package code.engine;

import code.math.VectorF;

/**
 * Created by theo on 24/06/17.
 */
public class GameProperties {
    private int score;
    private int gameTime;

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
}
