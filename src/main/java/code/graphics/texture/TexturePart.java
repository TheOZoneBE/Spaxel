package code.graphics.texture;

import code.math.VectorD;
import java.util.Map;
import code.graphics.RenderData;


public class TexturePart extends Renderable {

    private VectorD pos;
    private PackedTexture texture;
    private float[] coordinates;
    private String sheetName;

    public TexturePart() {
        super();
    }

    public void initializeCoordinates(Map<String, Texture> sheets) {
        Texture spritesheet = sheets.get(sheetName);

        VectorD sheetDim = spritesheet.getTextureDim();
        VectorD sheetPos = spritesheet.getTextureCoord();

        VectorD partPos = sheetPos.sum(sheetDim.elementMult(pos.elementDiv(spritesheet.getDim())));
        VectorD partDim = sheetDim.elementMult(dim.elementDiv(spritesheet.getDim()));

        coordinates = new float[] {(float) partPos.getValue(0), (float) partPos.getValue(1),
                (float) partDim.getValue(0), (float) partDim.getValue(1)};
    }

    /**
     * @param pos the pos to set
     */
    public void setPos(VectorD pos) {
        this.pos = pos;
    }

    public VectorD getPos() {
        return pos;
    }

    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setPackedTexture(PackedTexture texture) {
        this.texture = texture;
    }

    public void apply(RenderData data) {
        data.setTexOffset(coordinates);
        data.setSpriteSheetID(texture.getID());
        data.applyScale(dim);
    }
}
