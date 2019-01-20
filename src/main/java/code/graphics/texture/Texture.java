package code.graphics.texture;

import code.math.VectorD;
import code.graphics.buffer.RenderJob;

/**
 * Represents an image texture
 */
public class Texture extends Renderable {
    protected PackedTexture packedTexture;
    protected float[] coordinates;
    protected VectorD textureDim;
    protected VectorD textureCoord;
    protected VectorD pos;

    private String path;

    /**
     * Create a new Texture
     */
    public Texture() {
        super();
    }

    /**
     * Initialize the coordinates of this Texture using the data of the parent node
     * 
     * @param parent the parent node of this texture in the texture tree
     */
    public void initializeCoordinates(TextureNode parent) {
        Texture spritesheet = parent.getTexture();

        VectorD sheetPixelPos = parent.getPos();
        pos = parent.getPos();
        VectorD sheetPixelDim = spritesheet.getDim();

        VectorD ptextureDim = packedTexture.getDim();

        textureDim = sheetPixelDim.elementDiv(ptextureDim);
        textureCoord = sheetPixelPos.elementDiv(ptextureDim);

        initCoordinates();
    }

    protected void initCoordinates() {
        coordinates =
                new float[] {(float) textureCoord.getValue(0), (float) textureCoord.getValue(1),
                        (float) textureDim.getValue(0), (float) textureDim.getValue(1)};

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

    public float[] getPositioninates() {
        return coordinates;
    }

    public void setPackedTexture(PackedTexture packedTexture) {
        this.packedTexture = packedTexture;
    }

    public PackedTexture getPackedTexture() {
        return packedTexture;
    }

    public void apply(RenderJob data) {
        data.setTexOffset(coordinates);
        data.setSpriteSheetID(packedTexture.getID());
        data.applyScale(dim);
    }

    public Texture getSpritesheet() {
        return this;
    }
}
