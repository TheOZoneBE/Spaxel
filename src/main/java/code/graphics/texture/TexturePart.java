package code.graphics.texture;

import code.math.VectorD;


public class TexturePart extends Texture {
    private Texture spritesheet;
    private String sheetName;

    public TexturePart() {
        super();
    }

    public void initializeCoordinates(Texture spritesheet) {
        this.spritesheet = spritesheet;
        VectorD sheetDim = spritesheet.getTextureDim();
        VectorD sheetPos = spritesheet.getTextureCoord();

        textureCoord = sheetPos.sum(sheetDim.elementMult(pos.elementDiv(spritesheet.getDim())));
        textureDim = sheetDim.elementMult(dim.elementDiv(spritesheet.getDim()));

        initCoordinates();
    }



    public void setSheetName(String sheetName) {
        this.sheetName = sheetName;
    }

    public void setSpritesheet(Texture spritesheet) {
        this.spritesheet = spritesheet;
    }

    @Override
    public Texture getSpritesheet() {
        return spritesheet;
    }

    public String getSheetName() {
        return sheetName;
    }
}
