package code.loaders;

import code.engine.ItemCatalogue;
import code.engine.ItemProperties;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

public class ItemPropertiesLoader extends Loader {

    public ItemCatalogue loadItems(String path){
        try {
            super.loadFile(path);
            ObjectMapper mapper = new ObjectMapper();
            List<ItemProperties> itemProperties = mapper.readValue(file, new TypeReference<List<ItemProperties>>(){});
            return new ItemCatalogue(itemProperties);
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }
}
