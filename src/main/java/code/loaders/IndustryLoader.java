package code.loaders;

import code.factories.entities.EntityIndustry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * The IndustryLoader is responsible for loading all the Entityindustries
 * 
 * Created by theo on 3/06/17.
 */
public class IndustryLoader extends Loader {
    private static final Logger LOGGER = Logger.getLogger(IndustryLoader.class.getName());

    /**
     * Load the industries specified in the given files
     * 
     * @param industries list of paths to files that contain the properties for the industries
     * 
     * @return a map from the industryname to EntityIndustry object
     */
    public Map<String, EntityIndustry> loadEntityIndustries(String[] industries) {
        try {
            Map<String, EntityIndustry> industryMap = new HashMap<>();
            for (String s : industries) {
                loadFile(s);
                ObjectMapper mapper = new ObjectMapper();
                industryMap.putAll(
                        mapper.readValue(file, new TypeReference<Map<String, EntityIndustry>>() {
                        }));
            }
            return industryMap;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
