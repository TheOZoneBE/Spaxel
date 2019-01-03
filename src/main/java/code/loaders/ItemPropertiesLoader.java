package code.loaders;

import code.engine.ItemCatalogue;
import code.engine.ItemProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The ItemPropertiesLoader is responsible for loading the properties of the items
 */
public class ItemPropertiesLoader extends Loader {
    private static final Logger LOGGER = Logger.getLogger(ItemPropertiesLoader.class.getName());

    /**
     * Load the itemproperties specified in the given file
     * 
     * @param path the path to the file that contains the itemproperties
     * 
     * @return an itemcatalogue with the itemproperties
     */
    public ItemCatalogue loadItems(String path) {
        try {
            super.loadFile(path);
            ObjectMapper mapper = new ObjectMapper();
            List<ItemProperties> itemProperties =
                    mapper.readValue(file, new TypeReference<List<ItemProperties>>() {
                    });
            return new ItemCatalogue(itemProperties);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
