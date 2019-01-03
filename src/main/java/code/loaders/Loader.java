package code.loaders;

import java.io.InputStream;
import code.Game;

/**
 * Created by theo on 28/05/17.
 */
public class Loader {

    protected InputStream file;

    /**
     * Load a file into memory
     * 
     * @param path path to the file
     */
    public void loadFile(String path) {
        this.file = getClass().getResourceAsStream(path);
    }

    public static InputStream loadFileStatic(String path) {
        return Game.class.getResourceAsStream(path);
    }
}
