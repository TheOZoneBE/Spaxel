package code;

public final class Constants {
        public static final double FULL_CIRCLE = 2 * Math.PI;
        public static final double HALF_CIRLCE = Math.PI / 2;
        public static final int GAME_HEIGHT = 720;
        public static final int GAME_WIDTH = 1280;
        public static final long NS_PER_SECOND = 1_000_000_000;
        public static final int TICKS_PER_SECOND = 50;
        public static final long NS_PER_TICK = NS_PER_SECOND / TICKS_PER_SECOND;
        public static final long NS_PER_MS = 1_000_000;
        public static final double MOUSE_FOLLOW_CUTOFF = .1;
        public static final double MOUSE_FOLLOW_MULTIPLIER = .15;

        // game contstants
        public static final String GAME_NAME = "Spaxel";
        public static final int GAME_MAJOR_V = 0;
        public static final int GAME_MINOR_V = 4;
        public static final int GAME_PATCH_V = 0;
        public static final String DISPLAY_NAME =
                        GAME_NAME + " - " + GAME_MAJOR_V + "." + GAME_MINOR_V + "." + GAME_PATCH_V;

        // gl constants
        public static final int GL_MAJOR_V = 4;
        public static final int GL_MINOR_V = 5;

        // resources constants
        public static final String SPRITESHEET_PATH = "/resources/spritesheet.json";
        public static final String[] SPRITE_PATHS =
                        new String[] {"/resources/sprite.json", "/resources/font.json"};
        public static final String ANIMATION_PATH = "/resources/animation.json";
        public static final String MUSIC_PATH = "/resources/sound.json";
        public static final String HITSHAPE_PATH = "/resources/hitshape.json";
        public static final String ITEM_PATH = "/resources/itemProperties.json";
        public static final String[] STYLESHEET_PATHS = new String[] {"/ui/styles/common.json",
                        "/ui/styles/main.json", "/ui/styles/play.json", "/ui/styles/credits.json",
                        "/ui/styles/options.json", "/ui/styles/class_select.json",
                        "/ui/styles/game_over.json"};
        public static final String[] UI_PATHS = new String[] {"/ui/main.xml", "/ui/play.xml",
                        "/ui/credits.xml", "/ui/options.xml", "/ui/class_selection.xml",
                        "/ui/game_over.xml"};
        public static final String[] INDUSTRY_PATHS = new String[] {"/resources/entity.json",
                        "/resources/actor.json", "/resources/projectile.json",
                        "/resources/item.json", "/resources/player.json", "/resources/effect.json",
                        "/resources/marker.json"};

        // load resources constants
        public static final String LOAD_SPRITESHEET_PATH = "/loading/spritesheet.json";
        public static final String[] LOAD_SPRITE_PATHS =
                        new String[] {"/loading/sprite.json", "/resources/font.json"};
        public static final String LOAD_ANIMATION_PATH = "/loading/animation.json";
        public static final String[] LOAD_STYLESHEET_PATHS =
                        new String[] {"/ui/styles/common.json", "/ui/styles/load.json"};
        public static final String[] LOAD_UI_PATHS = new String[] {"/ui/load.xml"};


        private Constants() {

        }

}
