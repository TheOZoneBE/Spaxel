package code.engine;

import static code.loaders.Loader.*;
import java.util.Map;
import code.collision.HitShape;
import code.factories.entities.EntityIndustry;
import code.graphics.SpriteData;
import code.graphics.Spritesheet;
import code.graphics.animation.Animation;
import code.ui.elements.UI;
import code.ui.elements.UIType;
import code.ui.styles.Style;
import code.Constants;

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

	private Resources() {

	}

	public void initLoadingResources() {
		spritesheets = loadSpritesheets(Constants.SPRITESHEET_PATH);
		spriteAtlas = loadSpriteDatas(Constants.SPRITE_PATHS);
		animationAtlas = loadAnimations(Constants.ANIMATION_PATH);
		stylesheets = loadStylesheets(Constants.LOAD_STYLESHEET_PATHS);
		uis = loadUI(Constants.LOAD_UI_PATHS);
		Engine.get().setCurrentUI(uis.get(UIType.LOAD));
	}

	public static Resources get() {
		return resources;
	}

	public void startLoading() {
		musicList = new MusicList(loadSounds(Constants.MUSIC_PATH));

		hitShapeAtlas = loadHitShapes(Constants.HITSHAPE_PATH);

		items = loadItems(Constants.ITEM_PATH);

		stylesheets = loadStylesheets(Constants.STYLESHEET_PATHS);

		uis = loadUI(Constants.UI_PATHS);

		industryMap = loadEntityIndustries(Constants.INDUSTRY_PATHS);

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
}
