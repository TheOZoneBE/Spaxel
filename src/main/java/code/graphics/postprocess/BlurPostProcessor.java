package code.graphics.postprocess;

import code.Game;
import code.graphics.FBO;
import code.graphics.shaders.BlurShaderProgram;
import code.graphics.shaders.ShaderProgram;
import code.math.VectorF;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by theod on 25-7-2017.
 */
public class BlurPostProcessor extends PostProcessor {
    private static final double BLUR_STRENGTH = 2.0;
    private static final int QUAD_VERTICES = 6;
    private FBO middle;

    public BlurPostProcessor(ShaderProgram program) {
        super(program);
        middle = new FBO();
    }

    @Override
    public void postProcess(FBO in, FBO out) {
        fboQuad.bind();
        program.enable();

        middle.bindBuffer();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        in.bindTexture();
        ((BlurShaderProgram) program).setDir(new VectorF(0, (float) BLUR_STRENGTH / Game.GAME_HEIGHT));
        glDrawElements(GL_TRIANGLES, QUAD_VERTICES, GL_UNSIGNED_BYTE, 0);
        middle.unbindBuffer();

        out.bindBuffer();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        middle.bindTexture();
        ((BlurShaderProgram) program).setDir(new VectorF((float) BLUR_STRENGTH / Game.GAME_WIDTH, 0));
        glDrawElements(GL_TRIANGLES, QUAD_VERTICES, GL_UNSIGNED_BYTE, 0);
        out.unbindBuffer();
    }
}
