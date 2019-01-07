package code.graphics.texture;

import code.math.VectorD;
import code.util.TextureUtil;
import code.graphics.buffer.RenderJob;

/**
 * Represents a texture tree packed together
 */
public class PackedTexture extends Renderable {
    private TextureNode root;
    private int id;

    /**
     * Create a new PackedTexture with the given tree root
     * 
     * @param root the root of the tree
     */
    public PackedTexture(TextureNode root) {
        this.root = root;
    }

    /**
     * Initializes the coordinates for all the members of the tree
     */
    public void initializeCoordinates() {
        root.initializeCoordinates(this);
    }

    /**
     * Load the data of this texture tree and generate a new texture with it.
     */
    public void load() {
        this.id = TextureUtil.createGPUTexture(root.getDim(), root.getDim(),
                TextureUtil.loadTextureTree(root));
        this.dim = new VectorD(root.getDim(), root.getDim());
    }

    public int getID() {
        return id;
    }

    public void apply(RenderJob data) {
        data.setTexOffset(new float[] {0.0F, 0.0F, 1.0F, 1.0F});
        data.setSpriteSheetID(id);
        data.applyScale(dim);
    }

}
