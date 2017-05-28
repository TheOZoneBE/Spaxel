package code.loaders;

import java.io.File;
import java.io.InputStream;

/**
 * Created by theo on 28/05/17.
 */
public class Loader {

    protected InputStream file;

    public void loadFile(String path){
        this.file = getClass().getResourceAsStream(path);
    }
}
