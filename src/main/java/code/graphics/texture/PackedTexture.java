package code.graphics.texture;

import java.util.Map;
import code.math.VectorD;
import code.util.TextureUtil;
import code.graphics.RenderData;

public class PackedTexture extends Renderable {
    private TextureNode root;
    private Map<String, TextureNode> nodes;
    private int id;

    public PackedTexture(TextureNode root) {
        this.root = root;
    }

    public void initializeCoordinates() {
        root.initializeCoordinates(this);
    }

    public TextureNode getTextureNode(String name) {
        return nodes.get(name);
    }

    public void initialize() {
        this.id = TextureUtil.createGPUTexture(root.getDim(), root.getDim(),
                TextureUtil.loadTextureTree(root));
        this.dim = new VectorD(root.getDim(), root.getDim());
    }

    public int getID() {
        return id;
    }

    public void apply(RenderData data) {
        data.setTexOffset(new float[] {0.0F, 0.0F, 1.0F, 1.0F});
        data.setSpriteSheetID(id);
        data.applyScale(dim);
    }

}
