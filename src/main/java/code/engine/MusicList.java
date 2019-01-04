package code.engine;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import code.sound.Music;
import code.util.SpaxelRandom;

/**
 * Created by theo on 12/01/18.
 */
public class MusicList {
    private List<Music> alreadyPlayed;
    private Map<String, Music> music;
    private SpaxelRandom random;
    private int[] probabilities = { 0, 0, 0, 10, 5, 1, 3, 10, 3, 1, 5, 10 };

    public MusicList(Map<String, Music> music) {
        this.music = music;
        this.alreadyPlayed = new ArrayList<>();
        this.random = new SpaxelRandom();
    }

    public Music getRandomSong() {
        Map<Integer, Music> probabilityMap = new HashMap<>();
        int acc = 0;
        int time = Engine.get().getGameState().getGameTime();
        for (Music m : music.values()) {
            if (!alreadyPlayed.contains(m)) {
                if (time < 600) {
                    acc += probabilities[m.getIntensity()];
                } else if (time < 1200) {
                    acc += probabilities[3 + m.getIntensity()];
                } else {
                    acc += probabilities[6 + m.getIntensity()];
                }
            } else {
                acc += 1;
            }
            if (m.getIntensity() != 0) {
                probabilityMap.put(acc, m);
            }
        }
        int r = random.nextInt(acc);
        while (probabilityMap.get(r) == null) {
            r++;
        }
        alreadyPlayed.add(probabilityMap.get(r));
        return probabilityMap.get(r);
    }

    public void reset() {
        alreadyPlayed = new ArrayList<>();
    }

    public Music getSong(String name) {
        return music.get(name);
    }

}
