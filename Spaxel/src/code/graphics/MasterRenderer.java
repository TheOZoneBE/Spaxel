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


    public MasterRenderer(){
        initialize();
    }

    private void loadBuffer(int bufferID, FloatBuffer buffer){
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
    }

    public float[] testVertices = new float[]{
            -.5f,-.5f,0,
            -.5f,.5f,0,
            .5f,.5f,0,
            .5f,-.5f,0
    };

    byte[] indices = new byte[] {
            0, 1, 3,
            3, 1, 2
    };

    float[] tcs = new float[] {
            0, 1,
            0, 0,
            1, 0,
            1, 1
    };

    int shaderprogram;

    private int vbo, vao,ibo, tbo, texOffsetScale, trSc, sinCosAlpha;

    public void initialize(){
        shaderprogram = ShaderUtils.load("/shaders/2Dsprite.vert", "/shaders/2Dsprite.frag");

        MatrixF projection_matrix = MatrixMaker.orthographic(-GAME_WIDTH/2, GAME_WIDTH/2, -GAME_HEIGHT/2, GAME_HEIGHT/2, -1.0f, 1.0f);


        glUseProgram(shaderprogram);

        int uniform_pr = glGetUniformLocation(shaderprogram, "projection_matrix");
        int uniform_sa = glGetUniformLocation(shaderprogram, "tex_sampler");

        glUniformMatrix4fv(uniform_pr, false, projection_matrix.toFloatBuffer());
        glUniform1i(uniform_sa, 1);

        vao = glGenVertexArrays();
        glBindVertexArray(vao);

        vbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, vbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(testVertices), GL_STATIC_DRAW);
        glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(0);

        tbo = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, tbo);
        glBufferData(GL_ARRAY_BUFFER, BufferUtils.createFloatBuffer(tcs), GL_STATIC_DRAW);
        glVertexAttribPointer(1, 2, GL_FLOAT, false, 0, 0);
        glEnableVertexAttribArray(1);

        ibo = glGenBuffers();
        glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, ibo);
        glBufferData(GL_ELEMENT_ARRAY_BUFFER, BufferUtils.createByteBuffer(indices), GL_STATIC_DRAW);

        trSc = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, trSc);
        glEnableVertexAttribArray(2);
        glVertexAttribPointer(2,4,GL_FLOAT, false, 4*4,0);
        glVertexAttribDivisor(2,1);

        texOffsetScale = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, texOffsetScale);
        glEnableVertexAttribArray(3);
        glVertexAttribPointer(3,4,GL_FLOAT, false, 4*4,0);
        glVertexAttribDivisor(3,1);

        sinCosAlpha = glGenBuffers();
        glBindBuffer(GL_ARRAY_BUFFER, sinCosAlpha);
        glEnableVertexAttribArray(4);
        glVertexAttribPointer(4,4,GL_FLOAT, false, 4*4,0);
        glVertexAttribDivisor(4,1);
    }

    public void render(MasterBuffer masterBuffer){
        Map<Integer, List<RenderData>> buffers = masterBuffer.getData();
        for(Integer i: buffers.keySet()){
            render(new RenderBuffer(buffers.get(i)), i);
        }
    }

    public void render(RenderBuffer buffer, int spritesheet) {
        if (buffer.size() >0){
            loadBuffer(trSc, buffer.getTrscBuffer());
            loadBuffer(sinCosAlpha, buffer.getSinCosBuffer());
            loadBuffer(texOffsetScale, buffer.getTexOffsetBuffer());
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            glBindTexture(GL_TEXTURE_2D, spritesheet);

            glDrawElementsInstanced(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE,0,buffer.size());
        }


        //TODO text rendering

    }

}
