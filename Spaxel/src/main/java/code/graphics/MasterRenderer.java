package code.graphics;

import static code.Game.GAME_HEIGHT;
import static code.Game.GAME_WIDTH;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL13.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL31.*;
import static org.lwjgl.opengl.GL33.*;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;

import code.Game;
import code.math.MatrixF;
import code.math.MatrixMaker;
import code.util.BufferUtils;
import code.util.ShaderUtils;
import org.lwjgl.opengl.GLCapabilities;

/**
 * holds all the spritesheets and data of shaders, vertices, can switch between spritesheets, load the buffers and render
 * instanced.
 *
 *
 */
public class MasterRenderer {
    private Quad fboQuad;
    private InstancedQuad allQuad;
    private InstancedShaderProgram instancedShader;

    public MasterRenderer(){
        initialize();
    }

    public void initialize(){
        instancedShader = new InstancedShaderProgram("/shaders/2Dsprite.vert", "/shaders/2Dsprite.frag");
        instancedShader.enable();

        MatrixF projectionMatrix = MatrixMaker.orthographic(-GAME_WIDTH/2, GAME_WIDTH/2, -GAME_HEIGHT/2, GAME_HEIGHT/2, -1.0f, 1.0f);

        instancedShader.setTexSampler(1);
        instancedShader.setProjectionMatrix(projectionMatrix);

        allQuad = new InstancedQuad();
        fboQuad = new Quad();
    }

    public void render(MasterBuffer masterBuffer){
        allQuad.bind();
        Map<Integer, List<RenderData>> buffers = masterBuffer.getData();
        for(Integer i: buffers.keySet()){
            render(new RenderBuffer(buffers.get(i)), i);
        }
    }

    public void render(RenderBuffer buffer, int spritesheet) {
        if (buffer.size() >0){
            loadBuffer(allQuad.getTransScale(), buffer.getTrscBuffer());
            loadBuffer(allQuad.getSinCosAlphaColor(), buffer.getSinCosBuffer());
            loadBuffer(allQuad.getTexOffsetScale(), buffer.getTexOffsetBuffer());
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            glBindTexture(GL_TEXTURE_2D, spritesheet);

            glDrawElementsInstanced(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE,0,buffer.size());
        }
    }

    private void loadBuffer(int bufferID, FloatBuffer buffer){
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
    }

}
