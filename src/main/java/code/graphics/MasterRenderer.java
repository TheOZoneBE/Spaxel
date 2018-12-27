package code.graphics;

import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_DEPTH_BUFFER_BIT;
import static org.lwjgl.opengl.GL11.GL_TEXTURE_2D;
import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL11.GL_UNSIGNED_BYTE;
import static org.lwjgl.opengl.GL11.glBindTexture;
import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.glDrawElements;
import static org.lwjgl.opengl.GL15.GL_ARRAY_BUFFER;
import static org.lwjgl.opengl.GL15.GL_DYNAMIC_DRAW;
import static org.lwjgl.opengl.GL15.glBindBuffer;
import static org.lwjgl.opengl.GL15.glBufferData;
import static org.lwjgl.opengl.GL31.glDrawElementsInstanced;

import java.nio.FloatBuffer;
import java.util.List;
import java.util.Map;

import code.graphics.postprocess.BlurPostProcessor;
import code.graphics.postprocess.PostProcessor;
import code.graphics.shaders.BlurShaderProgram;
import code.graphics.shaders.InstancedShaderProgram;
import code.graphics.shaders.LastPassShaderProgram;
import code.math.MatrixD;
import code.math.MatrixMaker;
import code.math.VectorD;
import code.Constants;

/**
 * holds all the spritesheets and data of shaders, vertices, can switch between
 * spritesheets, load the buffers and render instanced.
 *
 *
 */
public class MasterRenderer {

    private InstancedQuad allQuad;
    private InstancedShaderProgram instancedShader;
    private PostProcessor blurPostProcessor;

    private LayerFBO layerFBO;

    private Quad fboQuad;
    private FBO firstPass;
    private FBO ui;
    private FBO blurred;
    private FBO lensed;
    private LastPassShaderProgram lastPassProgram;
    private BlurShaderProgram blurPassProgram;
    private LastPassShaderProgram lensPassProgram;

    public MasterRenderer() {
        initialize();
    }

    public void initialize() {
        MatrixD projectionMatrix = MatrixMaker.orthographic(-Constants.GAME_WIDTH / 2, Constants.GAME_WIDTH / 2,
                -Constants.GAME_HEIGHT / 2, Constants.GAME_HEIGHT / 2, -1.0f, 1.0f);

        blurPassProgram = new BlurShaderProgram("/shaders/blur_pass.vert", "/shaders/blur_pass.frag");
        blurPassProgram.enable();
        blurPassProgram.setTexSampler(1);
        blurPassProgram.setRadius(1);
        blurPassProgram.setResolution(2);
        blurPassProgram.setSize(9);
        blurPostProcessor = new BlurPostProcessor(blurPassProgram);

        lastPassProgram = new LastPassShaderProgram("/shaders/last_pass.vert", "/shaders/last_pass.frag");
        lastPassProgram.enable();
        lastPassProgram.setTexSampler(1);
        lastPassProgram.setProjectionMatrix(projectionMatrix);
        lastPassProgram.setTranslationMatrix(
                MatrixMaker.getTransformationMatrix(new VectorD(0, 0), 0, Constants.GAME_WIDTH, Constants.GAME_HEIGHT));

        instancedShader = new InstancedShaderProgram("/shaders/2Dsprite.vert", "/shaders/2Dsprite.frag");
        instancedShader.enable();
        instancedShader.setTexSampler(1);
        instancedShader.setProjectionMatrix(projectionMatrix);

        allQuad = new InstancedQuad();
        fboQuad = new Quad();
        blurred = new FBO();
        layerFBO = new LayerFBO();
    }

    public void render(MasterBuffer masterBuffer) {
        allQuad.bind();
        instancedShader.enable();
        // render all layers
        for (RenderLayer layer : RenderLayer.values()) {
            layerFBO.getFbo(layer).bindBuffer();
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            Map<Integer, List<RenderData>> buffers = masterBuffer.getData(layer);
            for (Integer i : buffers.keySet()) {
                render(new RenderBuffer(buffers.get(i)), i);
            }
            layerFBO.getFbo(layer).unbindBuffer();
        }

        blurPostProcessor.postProcess(layerFBO.getFbo(RenderLayer.GAME), blurred);

        combine();
    }

    public void combine() {
        fboQuad.bind();
        lastPassProgram.enable();
        glBindBuffer(GL_ARRAY_BUFFER, 0);
        blurred.bindTexture();

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);

        layerFBO.getFbo(RenderLayer.GAME).bindTexture();

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);

        layerFBO.getFbo(RenderLayer.UI).bindTexture();

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);

        layerFBO.getFbo(RenderLayer.UI).bindTexture();

        glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0);
    }

    public void render(RenderBuffer buffer, int spritesheet) {
        if (buffer.size() > 0) {
            loadBuffer(allQuad.getTransScale(), buffer.getTrscBuffer());
            loadBuffer(allQuad.getSinCosAlphaColor(), buffer.getSinCosBuffer());
            loadBuffer(allQuad.getTexOffsetScale(), buffer.getTexOffsetBuffer());
            glBindBuffer(GL_ARRAY_BUFFER, 0);

            glBindTexture(GL_TEXTURE_2D, spritesheet);

            glDrawElementsInstanced(GL_TRIANGLES, 6, GL_UNSIGNED_BYTE, 0, buffer.size());
        }
    }

    private void loadBuffer(int bufferID, FloatBuffer buffer) {
        glBindBuffer(GL_ARRAY_BUFFER, bufferID);
        glBufferData(GL_ARRAY_BUFFER, buffer, GL_DYNAMIC_DRAW);
    }

}
