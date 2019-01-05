package code.engine;

import static code.loaders.Loader.*;
import java.util.Map;
import java.util.List;
import code.collision.HitShape;
import code.factories.entities.EntityIndustry;
import code.graphics.SpriteData;
import code.graphics.Spritesheet;
import code.graphics.animation.Animation;
import code.ui.elements.UI;
import code.ui.elements.UIType;
import code.ui.styles.Style;
import code.Constants;
import code.input.Key;
import code.input.KeyState;

/**
 * Singleton class to hold all the game resources
 */
public final class Resources {
	private static final Resources resources = new Resources();

	private MusicList musicList;
	private Map<String, EntityIndustry> industryMap;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<UIType, UI> uis;
	private Map<String, Map<String, Style>> stylesheets;
	private Map<String, Spritesheet> spritesheets;
	private Map<String, SpriteData> spriteAtlas;
	private Map<String, Animation> animationAtlas;
	private ItemCatalogue items;
	private Map<Key, KeyState> keyConfiguration;

	private Resources() {

	}

	/**
	 * Initializes the resources needed to show the loadingscreen
	 */
	public void initLoadingResources() {
		Map<String, List<String>> resourcePaths = loadResourcePaths(Constants.LOAD_RESOURCE_PATH);
		spritesheets = loadSpritesheets(resourcePaths.get("spritesheet"));
		spriteAtlas = loadSpriteDatas(resourcePaths.get("sprite"));
		animationAtlas = loadAnimations(resourcePaths.get("animation"));
		stylesheets = loadStylesheets(resourcePaths.get("stylesheet"));
		uis = loadUI(resourcePaths.get("ui"));
		Engine.get().setCurrentUI(uis.get(UIType.LOAD));
	}

	/**
	 * Get the singleton instance of this class
	 * 
	 * @return the singleton
	 */
	public static Resources get() {
		return resources;
	}

	/**
	 * Starts the loading of all the game resources
	 */
	public void startLoading() {
		Map<String, List<String>> resourcePaths = loadResourcePaths(Constants.RESOURCE_PATH);

		musicList = new MusicList(loadSounds(resourcePaths.get("music")));

		hitShapeAtlas = loadHitShapes(resourcePaths.get("hitshape"));

		items = loadItems(resourcePaths.get("item"));

		stylesheets = loadStylesheets(resourcePaths.get("stylesheet"));

		uis = loadUI(resourcePaths.get("ui"));

		industryMap = loadEntityIndustries(resourcePaths.get("industry"));

		keyConfiguration = loadKeyConfiguration(resourcePaths.get("keys"));

		Engine.get().finishLoading();
	}

	public Map<String, Map<String, Style>> getStylesheets() {
		return stylesheets;
	}

	public Map<String, Animation> getAnimationAtlas() {
		return animationAtlas;
	}


	public Map<String, Spritesheet> getSpritesheets() {
		return spritesheets;
	}

	public Map<String, EntityIndustry> getIndustryMap() {
		return industryMap;
	}

	public Map<String, HitShape> getHitShapeAtlas() {
		return hitShapeAtlas;
	}

	public Map<UIType, UI> getUIS() {
		return uis;
	}

	public MusicList getMusicList() {
		return musicList;
	}

	public ItemCatalogue getItems() {
		return items;
	}

	public Map<String, SpriteData> getSpriteAtlas() {
		return spriteAtlas;
	}

	public Map<Key, KeyState> getKeyConfiguration() {
		return keyConfiguration;
	}
}
