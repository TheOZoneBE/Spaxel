package code.loaders;

import code.factories.entities.EntityIndustry;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;

/**
 * Created by theo on 3/06/17.
 */
public class IndustryLoader extends Loader {

    public Map<String, EntityIndustry> loadEntityIndustries(String industries){
        try {
            loadFile(industries);
            ObjectMapper mapper = new ObjectMapper();
            Map<String, EntityIndustry> industryMap = mapper.readValue(file, new TypeReference<Map<String, EntityIndustry>>(){});
            return industryMap;
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
