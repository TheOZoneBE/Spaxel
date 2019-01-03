package code.ui.render;

import java.util.List;
import java.util.ArrayList;
import code.engine.Resources;
import code.graphics.MasterBuffer;
import code.graphics.RenderData;
import code.graphics.RenderLayer;
import code.graphics.SpriteData;
import code.math.VectorD;
import code.ui.styles.Style;

public final class TextRenderer {
    private static final String SPACE = " ";
    private static final String NEWLINE = "\\\\";
    private static final String L_BRACKET = "{";
    private static final String R_BRACKET = "}";

    private static final int SPACING = 10;
    private static final int TWO = 2;
    private static final int NEWLINE_OFFSET = 16;

    private TextRenderer() {

    }

    public static void renderText(Style style, MasterBuffer buffer) {
        String text = style.getProperty("text");
        double scale = Double.parseDouble(style.getProperty("text-scale"));
        boolean alignLeft = "left".equals(style.getProperty("align"));
        double x = Double.parseDouble(style.getProperty("x"));
        double y = Double.parseDouble(style.getProperty("y"));
        VectorD pos = new VectorD(x, y);

        String[] lines = text.split(NEWLINE);
        VectorD offset = new VectorD(0, 0);
        for (String line : lines) {
            List<Character> characters = getCharacters(line);
            if (!alignLeft) {
                offset.setValue(0, calculateOffset(characters) * scale);
            }

            for (Character character : characters) {
                VectorD charOffset = offset.sum(new VectorD(character.getWidth() * scale / TWO, 0));
                character.render(pos.sum(charOffset), scale, buffer);
                offset.setValue(0, offset.getValue(0) + character.getWidth() * scale);
            }
            offset.setValue(1, offset.getValue(1) - NEWLINE_OFFSET * scale);
        }

    }

    private static List<Character> getCharacters(String line) {
        List<Character> characters = new ArrayList<>();
        int i = 0;
        while (i < line.length()) {
            String c = line.substring(i, i + 1);
            if (c.equals(L_BRACKET)) {
                int start = i + 1;
                while (!c.equals(R_BRACKET)) {
                    i++;
                    c = line.substring(i, i + 1);
                }
                characters.add(new Character(
                        Resources.get().getSpriteAtlas().get(line.substring(start, i))));

            } else if (c.equals(SPACE)) {
                characters.add(new Character(SPACING));

            } else {
                characters.add(new Character(Resources.get().getSpriteAtlas().get(c)));
            }
            i++;

        }
        return characters;
    }

    private static double calculateOffset(List<Character> line) {
        return -calculateLineWidth(line) / TWO;
    }

    private static double calculateLineWidth(List<Character> line) {
        return line.stream().map(Character::getWidth).reduce(0.,
                (Double accWidth, Double width) -> accWidth += width);
    }

    private static class Character {
        private double width;
        private SpriteData sprite;

        public Character(SpriteData sprite) {
            this.sprite = sprite;
            this.width = sprite.getDim().getValue(0);
        }

        public Character(double width) {
            this.width = width;
        }

        public double getWidth() {
            return width;
        }

        public void render(VectorD position, double scale, MasterBuffer buffer) {
            if (sprite != null) {
                RenderData data = new RenderData();
                data.applyTranslation(position);
                data.applyRot(0);
                data.applyScale(scale);
                data.setSprite(sprite);
                buffer.addNewSprite(RenderLayer.UI, data);
            }
        }
    }
}
