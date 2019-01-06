package code.graphics.texture;

import code.math.VectorD;
import code.graphics.RenderData;


public class Texture extends Renderable {
    private String path;
    private PackedTexture packedTexture;
    private VectorD textureDim;
    private VectorD textureCoord;
    private float[] coordinates;

    public void initializeCoordinates(TextureNode parent) {
        Texture spritesheet = parent.getTexture();
        VectorD sheetPixelPos = parent.getPos();
        VectorD sheetPixelDim = spritesheet.getDim();
        VectorD ptextureDim = packedTexture.getDim();

        textureDim = sheetPixelDim.elementDiv(ptextureDim);
        textureCoord = sheetPixelPos.elementDiv(ptextureDim);

        coordinates =
                new float[] {(float) textureCoord.getValue(0), (float) textureCoord.getValue(1),
                        (float) textureDim.getValue(0), (float) textureDim.getValue(1)};

    }


    public VectorD getDim() {
        return dim;
    }

    public void setDim(VectorD dim) {
        this.dim = dim;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public VectorD getTextureDim() {
        return textureDim;
    }

    public VectorD getTextureCoord() {
        return textureCoord;
    }

    public float[] getCoordinates() {
        return coordinates;
    }

    public void setPackedTexture(PackedTexture packedTexture) {
        this.packedTexture = packedTexture;
    }

    public void apply(RenderData data) {
        data.setTexOffset(coordinates);
        data.setSpriteSheetID(packedTexture.getID());
        data.applyScale(dim);
    }
}
