package code.graphics;

import static org.lwjgl.opengl.GL11.GL_FLOAT;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glGenBuffers;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL33.glVertexAttribDivisor;

/**
 * Created by theod on 16-7-2017.
 */
public class InstancedQuad extends Quad {
    private int texOffsetScale;
    private int transScale;
    private int sinCosAlphaColor;

    public InstancedQuad() {
        super();

        transScale = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, transScale);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2, 4, GL_FLOAT, false, 4 * 4, 0);
        glVertexAttribDivisor(2, 1);

        texOffsetScale = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, texOffsetScale);
        glEnableVertexAttribArray(3);
        glVertexAttribPointer(3, 4, GL_FLOAT, false, 4 * 4, 0);
        glVertexAttribDivisor(3, 1);

        sinCosAlphaColor = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, sinCosAlphaColor);
        glEnableVertexAttribArray(4);
        glVertexAttribPointer(4, 4, GL_FLOAT, false, 4 * 4, 0);
        glVertexAttribDivisor(4, 1);
    }

    public int getTexOffsetScale() {
        return texOffsetScale;
    }

    public int getTransScale() {
        return transScale;
    }

    public int getSinCosAlphaColor() {
        return sinCosAlphaColor;
    }
}
