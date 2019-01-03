package code.loaders;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.databind.ObjectMapper;
import code.ui.styles.Style;
import java.util.logging.Level;
import com.fasterxml.jackson.core.type.TypeReference;
import java.util.logging.Logger;

/**
 * The StylesheetLoader is responsible for loading all the stylesheets the uis use
 */
public class StylesheetLoader extends Loader {
    private static final Logger LOGGER = Logger.getLogger(StylesheetLoader.class.getName());

    /**
     * Load the stylesheets in the given files
     * 
     * @param sheets a list of paths to the stylesheets
     * 
     * @return a map of the stylesheet name to a map of the style identifier to the style object
     */
    public Map<String, Map<String, Style>> loadStylesheets(String[] sheets) {
        try {
            Map<String, Map<String, Style>> stylesheets = new HashMap<>();
            for (String s : sheets) {
                loadFile(s);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Style> sheet =
                        mapper.readValue(file, new TypeReference<Map<String, Style>>() {
                        });
                stylesheets.put(s, sheet);
            }
            return stylesheets;
        } catch (IOException e) {

            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
