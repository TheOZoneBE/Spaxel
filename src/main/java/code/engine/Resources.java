package code.engine;

import static code.loaders.Loader.*;
import java.util.Map;
import java.util.List;
import code.collision.HitShape;
import code.factories.entities.EntityIndustry;
import code.graphics.animation.Animation;
import code.ui.elements.UI;
import code.ui.elements.UIType;
import code.util.TextureUtil;
import code.Constants;
import code.input.Key;
import code.input.KeyState;
import code.sound.Music;
import code.sound.Sound;
import code.graphics.texture.Texture;
import code.graphics.texture.TexturePart;
import code.graphics.texture.ColorBox;
import code.graphics.texture.PackedTexture;
import code.graphics.texture.Renderable;
import java.util.HashMap;

/**
 * Singleton class to hold all the game resources
 */
public final class Resources {
	private static final Resources resources = new Resources();

	private Map<String, EntityIndustry> industryMap;
	private Map<String, HitShape> hitShapeAtlas;
	private Map<UIType, UI> uis;
	private Map<String, Map<String, Map<String, String>>> stylesheets;
	private Map<String, Animation> animationAtlas;
	private ItemCatalogue items;
	private Map<Key, KeyState> keyConfiguration;
	private Map<String, Renderable> renderables;
	private Map<String, Sound> sounds;
	private Map<String, Music> music;

	private Resources() {

	}

	/**
	 * Initializes the resources needed to show the loadingscreen
	 */
	public void initLoadingResources() {
		Map<String, List<String>> resourcePaths = loadResourcePaths(Constants.LOAD_RESOURCE_PATH);
		animationAtlas = loadAnimations(resourcePaths.get("animation"));
		stylesheets = loadStylesheets(resourcePaths.get("stylesheet"));
		uis = loadUI(resourcePaths.get("ui"));
		// Renderables loading
		renderables = new HashMap<>();
		Map<String, Texture> textures = loadTextures(resourcePaths.get("texture"));
		PackedTexture packedTexture = TextureUtil.packTextures(textures.values());
		packedTexture.load();
		packedTexture.initializeCoordinates();
		Map<String, TexturePart> textureParts = loadTextureParts(resourcePaths.get("texture_part"));
		for (TexturePart tPart : textureParts.values()) {
			tPart.setPackedTexture(packedTexture);
			tPart.initializeCoordinates(textures.get(tPart.getSheetName()));
		}
		Map<String, ColorBox> colorBoxes = loadColorBoxes(resourcePaths.get("color_box"));
		renderables.putAll(textures);
		renderables.putAll(textureParts);
		renderables.putAll(colorBoxes);
		renderables.put("packed", packedTexture);


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

		music = loadMusic(resourcePaths.get("music"));

		// sounds = loadSounds(resourcePaths.get("sounds"));

		hitShapeAtlas = loadHitShapes(resourcePaths.get("hitshape"));

		items = loadItems(resourcePaths.get("item"));

		stylesheets = loadStylesheets(resourcePaths.get("stylesheet"));

		uis = loadUI(resourcePaths.get("ui"));

		industryMap = loadEntityIndustries(resourcePaths.get("industry"));

		keyConfiguration = loadKeyConfiguration(resourcePaths.get("keys"));

		Engine.get().finishLoading();
	}

	public void exit() {
		for (Music m: music.values()){
			m.close();
		}
		music.clear();
		// TODO sounds
	}

	public Map<String, Map<String, Map<String, String>>> getStylesheets() {
		return stylesheets;
	}

	public Map<String, Animation> getAnimationAtlas() {
		return animationAtlas;
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

	public Map<String, Music> getMusic() {
		return music;
	}

	public ItemCatalogue getItems() {
		return items;
	}

	public Map<Key, KeyState> getKeyConfiguration() {
		return keyConfiguration;
	}

	public Map<String, Renderable> getRenderables() {
		return renderables;
	}

}
