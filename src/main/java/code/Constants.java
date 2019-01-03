package code;

public final class Constants {
        public static final double FULL_CIRCLE = 2 * Math.PI;
        public static final double HALF_CIRLCE = Math.PI / 2;
        public static final int GAME_HEIGHT = 720;
        public static final int GAME_WIDTH = 1280;
        public static final int HALF_GAME_HEIGHT = GAME_HEIGHT / 2;
        public static final int HALF_GAME_WIDTH = GAME_WIDTH / 2;
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
        public static final String RESOURCE_PATH = "/resource_paths.yml";
        public static final String LOAD_RESOURCE_PATH = "/load_resource_paths.yml";

        private Constants() {

        }

}
