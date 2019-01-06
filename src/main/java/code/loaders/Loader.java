package code.loaders;

import java.io.IOException;
import java.io.InputStream;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import code.collision.HitShape;
import code.engine.ItemCatalogue;
import code.engine.ItemProperties;
import code.factories.entities.EntityIndustry;
import code.graphics.SpriteData;
import code.graphics.Spritesheet;
import code.graphics.animation.Animation;
import code.sound.Music;
import code.ui.elements.UI;
import code.ui.elements.UIType;
import code.ui.styles.Style;
import code.input.Key;
import code.input.KeyState;



/**
 * Created by theo on 28/05/17.
 */
public final class Loader {
    private static final Logger LOGGER = Logger.getLogger(Loader.class.getName());

    private Loader() {
    }

    /**
     * Load a file as an inputstream
     * 
     * @param path path to the file
     * 
     * @return the file as inputstream
     */
    public static InputStream loadFile(String path) {
        return Loader.class.getResourceAsStream(path);
    }

    /**
     * Load the animations specified at the given file
     * 
     * @param files the paths to the files that contain the properties of the animations
     * 
     * @return map from animation name to Animation object
     */
    public static Map<String, Animation> loadAnimations(Iterable<String> files) {
        try {
            Map<String, Animation> animations = new HashMap<>();
            for (String path : files) {
                InputStream file = loadFile(path);
                ObjectMapper mapper = new ObjectMapper();
                animations
                        .putAll(mapper.readValue(file, new TypeReference<Map<String, Animation>>() {
                        }));
            }

            return animations;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    /**
     * Load the hitshapes specified at the given file
     * 
     * @param files the paths to the files that contain the properties of the hitshapes
     * 
     * @return map from hitshape name to HitShape object
     */
    public static Map<String, HitShape> loadHitShapes(Iterable<String> files) {
        try {
            Map<String, HitShape> hitshapes = new HashMap<>();
            for (String path : files) {
                InputStream file = loadFile(path);
                ObjectMapper mapper = new ObjectMapper();
                hitshapes.putAll(mapper.readValue(file, new TypeReference<Map<String, HitShape>>() {
                }));
            }

            return hitshapes;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    /**
     * Load the industries specified in the given files
     * 
     * @param industries list of paths to files that contain the properties for the industries
     * 
     * @return a map from the industryname to EntityIndustry object
     */
    public static Map<String, EntityIndustry> loadEntityIndustries(Iterable<String> industries) {
        try {
            Map<String, EntityIndustry> industryMap = new HashMap<>();
            for (String s : industries) {
                InputStream file = loadFile(s);
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

    /**
     * Load the itemproperties specified in the given file
     * 
     * @param files the paths to the files that contain the itemproperties
     * 
     * @return an itemcatalogue with the itemproperties
     */
    public static ItemCatalogue loadItems(List<String> files) {
        try {
            InputStream file = loadFile(files.get(0));
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

    /**
     * Load the sounds specified in the given files
     * 
     * @param files the paths to the files that contain the sounds
     * 
     * @return a map from sound name to Sound
     */
    public static Map<String, Music> loadSounds(Iterable<String> files) {
        try {
            Map<String, Music> music = new HashMap<>();
            for (String path : files) {
                InputStream file = loadFile(path);
                ObjectMapper mapper = new ObjectMapper();
                music.putAll(mapper.readValue(file, new TypeReference<Map<String, Music>>() {
                }));
            }
            for (Music m : music.values()) {
                m.initialize();
            }
            return music;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }


    /**
     * Load the SpriteData specified in the given files
     * 
     * @param sprites list of paths to files with the properties of each sprite
     * 
     * @return a map from sprite name to SpriteData object
     */
    public static Map<String, SpriteData> loadSpriteDatas(Iterable<String> sprites) {
        try {
            Map<String, SpriteData> spriteMap = new HashMap<>();
            for (String s : sprites) {
                InputStream file = loadFile(s);
                ObjectMapper mapper = new ObjectMapper();
                spriteMap.putAll(
                        mapper.readValue(file, new TypeReference<Map<String, SpriteData>>() {
                        }));
            }

            for (Map.Entry<String, SpriteData> entry : spriteMap.entrySet()) {
                entry.getValue().initialize();
                entry.getValue().setName(entry.getKey());
            }
            return spriteMap;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    /**
     * Load the spritesheets specified in the given file
     * 
     * @param files paths to the files that contain the properties for all spritesheets
     * 
     * @return a map from spritesheetname to spritesheet object
     */
    public static Map<String, Spritesheet> loadSpritesheets(Iterable<String> files) {
        try {
            Map<String, Spritesheet> spritesheetMap = new HashMap<>();
            for (String path : files) {
                InputStream file = loadFile(path);
                ObjectMapper mapper = new ObjectMapper();
                spritesheetMap.putAll(
                        mapper.readValue(file, new TypeReference<Map<String, Spritesheet>>() {
                        }));
            }
            for (Spritesheet s : spritesheetMap.values()) {
                s.load();
            }
            return spritesheetMap;
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    /**
     * Load the stylesheets in the given files
     * 
     * @param sheets a list of paths to the stylesheets
     * 
     * @return a map of the stylesheet name to a map of the style identifier to the style object
     */
    public static Map<String, Map<String, Map<String, String>>> loadStylesheets(
            Iterable<String> sheets) {
        try {
            Map<String, Map<String, Map<String, String>>> stylesheets = new HashMap<>();
            for (String s : sheets) {
                InputStream file = loadFile(s);
                ObjectMapper mapper = new ObjectMapper();
                Map<String, Map<String, String>> sheet = mapper.readValue(file,
                        new TypeReference<Map<String, Map<String, String>>>() {
                        });
                stylesheets.put(s, sheet);
            }
            return stylesheets;
        } catch (IOException e) {

            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    /**
     * Load the uis in the given files
     * 
     * @param uis a list with paths to the uis
     * 
     * @return a map that connect ui type to the correct ui object
     */
    public static Map<UIType, UI> loadUI(Iterable<String> uis) {
        try {
            EnumMap<UIType, UI> uiMap = new EnumMap<>(UIType.class);
            for (String ui : uis) {
                InputStream file = loadFile(ui);
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

    /**
     * Load the configuration file for all the paths to the resources
     * 
     * @param path the path to the file
     * 
     * @return a map of resource identifier to path list
     */
    public static Map<String, List<String>> loadResourcePaths(String path) {
        try {
            InputStream file = loadFile(path);
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());
            return mapper.readValue(file, new TypeReference<Map<String, List<String>>>() {
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }

    /**
     * Loads the key configuration file specified in the argument
     * 
     * @param path the path to the key configuration file
     * 
     * @return the key configuration
     */
    public static Map<Key, KeyState> loadKeyConfiguration(List<String> path) {
        try {
            InputStream file = loadFile(path.get(0));
            ObjectMapper mapper = new ObjectMapper(new YAMLFactory());

            return mapper.readValue(file, new TypeReference<Map<Key, KeyState>>() {
            });
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return null;
    }
}
