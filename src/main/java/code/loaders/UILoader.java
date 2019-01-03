package code.loaders;

import java.io.IOException;
import java.util.EnumMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import code.ui.elements.UIType;
import code.ui.elements.UI;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;

/**
 * The UILoader is responsible for loading the structure of the different uis in the game
 */
public class UILoader extends Loader {
    private static final Logger LOGGER = Logger.getLogger(UILoader.class.getName());

    /**
     * Load the uis in the given files
     * 
     * @param uis a list with paths to the uis
     * 
     * @return a map that connect ui type to the correct ui object
     */
    public Map<UIType, UI> loadUI(String[] uis) {
        try {
            EnumMap<UIType, UI> uiMap = new EnumMap<>(UIType.class);
            for (String ui : uis) {
                loadFile(ui);
                XmlMapper mapper = new XmlMapper();
                UI root = mapper.readValue(file, UI.class);
                root.initialize();
                uiMap.put(root.getType(), root);
            }
            return uiMap;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }



}
