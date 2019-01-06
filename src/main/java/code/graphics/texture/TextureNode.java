package code.graphics.texture;

import code.math.VectorD;

public class TextureNode {
    private Texture texture;
    private int dim;
    private NodePlacement placement;
    private TextureNode parent;

    private TextureNode topLeft;
    private TextureNode topRight;
    private TextureNode botLeft;
    private TextureNode botRight;

    public TextureNode(Texture texture) {
        this.texture = texture;
        int maxDim = (int) Math.max(texture.getDim().getValue(0), texture.getDim().getValue(1));
        calcDim(maxDim);
    }

    public TextureNode(int dim) {
        this.dim = dim;
        this.placement = NodePlacement.TOP_LEFT;
    }

    public TextureNode(TextureNode topLeft, TextureNode topRight, TextureNode botLeft,
            TextureNode botRight) {
        this.placement = NodePlacement.TOP_LEFT;
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.botLeft = botLeft;
        this.botRight = botRight;
        this.dim = topLeft.getDim() * 2;
        topLeft.setPlacement(NodePlacement.TOP_LEFT);
        topRight.setPlacement(NodePlacement.TOP_RIGHT);
        botLeft.setPlacement(NodePlacement.BOT_LEFT);
        botRight.setPlacement(NodePlacement.BOT_RIGHT);
        topLeft.setParent(this);
        botLeft.setParent(this);
        topRight.setParent(this);
        botRight.setParent(this);
    }

    private void calcDim(int maxDim) {
        this.dim = 2;
        while (dim < maxDim) {
            dim *= 2;
        }
    }

    public Texture getTexture() {
        return texture;
    }

    public VectorD getPos() {
        VectorD pos;
        switch (placement) {
            case TOP_LEFT:
                pos = new VectorD(0, 0);
                break;
            case TOP_RIGHT:
                pos = new VectorD(dim, 0);
                break;
            case BOT_LEFT:
                pos = new VectorD(0, dim);
                break;
            case BOT_RIGHT:
                pos = new VectorD(dim, dim);
                break;
            default:
                pos = new VectorD(0, 0);
        }
        if (parent != null) {
            return parent.getPos().sum(pos);
        } else {
            return pos;
        }
    }

    private enum NodePlacement {
        TOP_LEFT, TOP_RIGHT, BOT_LEFT, BOT_RIGHT
    }

    public int getDim() {
        return dim;
    }

    public NodePlacement getPlacement() {
        return placement;
    }

    public void setPlacement(NodePlacement placement) {
        this.placement = placement;
    }

    public void setParent(TextureNode parent) {
        this.parent = parent;
    }

    public TextureNode getTopLeft() {
        return topLeft;
    }

    public TextureNode getTopRight() {
        return topRight;
    }

    public TextureNode getBotLeft() {
        return botLeft;
    }

    public TextureNode getBotRight() {
        return botRight;
    }

    /**
     * @return the parent
     */
    public TextureNode getParent() {
        return parent;
    }

    /**
     * @param topLeft the topLeft to set
     */
    public void setTopLeft(TextureNode topLeft) {
        this.topLeft = topLeft;
        topLeft.setPlacement(NodePlacement.TOP_LEFT);
        topLeft.setParent(this);
    }

    /**
     * @param topRight the topRight to set
     */
    public void setTopRight(TextureNode topRight) {
        this.topRight = topRight;
        topRight.setPlacement(NodePlacement.TOP_RIGHT);
        topRight.setParent(this);
    }

    /**
     * @param botLeft the botLeft to set
     */
    public void setBotLeft(TextureNode botLeft) {
        this.botLeft = botLeft;
        botLeft.setPlacement(NodePlacement.BOT_LEFT);
        botLeft.setParent(this);
    }

    /**
     * @param botRight the botRight to set
     */
    public void setBotRight(TextureNode botRight) {
        this.botRight = botRight;
        botRight.setPlacement(NodePlacement.BOT_RIGHT);
        botRight.setParent(this);
    }

    public void initializeCoordinates(PackedTexture packedTexture) {
        if (texture != null) {
            texture.setPackedTexture(packedTexture);
            texture.initializeCoordinates(this);
        } else {
            if (topLeft != null) {
                topLeft.initializeCoordinates(packedTexture);
            }
            if (topRight != null) {
                topRight.initializeCoordinates(packedTexture);
            }
            if (botLeft != null) {
                botLeft.initializeCoordinates(packedTexture);
            }
            if (botRight != null) {
                botRight.initializeCoordinates(packedTexture);
            }
        }
    }

}
