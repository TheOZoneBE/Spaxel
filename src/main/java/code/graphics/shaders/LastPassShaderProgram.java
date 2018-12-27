package code.graphics.shaders;

import code.math.MatrixD;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

/**
 * Created by theod on 16-7-2017.
 */
public class LastPassShaderProgram extends ShaderProgram {
    private int texSamplerLocation;
    private int projectionMatrixLocation;
    private int translationMatrixLocation;

    public LastPassShaderProgram(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
        texSamplerLocation = glGetUniformLocation(program, "tex_sampler");
        projectionMatrixLocation = glGetUniformLocation(program, "projection_matrix");
        translationMatrixLocation = glGetUniformLocation(program, "translation_matrix");
    }

    public void setTexSampler(int sampler) {
        glUniform1i(texSamplerLocation, sampler);
    }

    public void setProjectionMatrix(MatrixD projectionMatrix) {
        glUniformMatrix4fv(projectionMatrixLocation, false, projectionMatrix.toFloatBuffer());
    }

    public void setTranslationMatrix(MatrixD translationMatrix) {
        glUniformMatrix4fv(translationMatrixLocation, false, translationMatrix.toFloatBuffer());
    }
}
