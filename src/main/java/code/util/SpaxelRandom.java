package code.util;

import java.util.Random;
import java.util.List;

public class SpaxelRandom extends Random {
    private static final long serialVersionUID = 1;

    public SpaxelRandom() {
        super();
    }

    public <T> T choose(List<T> choices) {
        return choices.get(nextInt(choices.size()));
    }

    public <T> T choose(T... choices) {
        return choices[nextInt(choices.length)];
    }

    public int between(int min, int max) {
        return nextInt(max - min) + min;
    }

    public double between(double min, double max) {
        return min + nextDouble() * (max - min);
    }

    public boolean pass(double chance) {
        return nextDouble() < chance;
    }

    public double nextDouble(double multiplier) {
        return nextDouble() * multiplier;
    }
}