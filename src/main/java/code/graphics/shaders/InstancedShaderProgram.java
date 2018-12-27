package code.graphics.shaders;

import code.math.MatrixD;

import static org.lwjgl.opengl.GL20.glGetUniformLocation;
import static org.lwjgl.opengl.GL20.glUniform1i;
import static org.lwjgl.opengl.GL20.glUniformMatrix4fv;

/**
 * Created by theod on 16-7-2017.
 */
public class InstancedShaderProgram extends ShaderProgram {
    private int texSamplerLocation;
    private int projectionMatrixLocation;

    public InstancedShaderProgram(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
        texSamplerLocation = glGetUniformLocation(program, "tex_sampler");
        projectionMatrixLocation = glGetUniformLocation(program, "projection_matrix");
    }

    public void setTexSampler(int sampler) {
        glUniform1i(texSamplerLocation, sampler);
    }

    public void setProjectionMatrix(MatrixD projectionMatrix) {
        glUniformMatrix4fv(projectionMatrixLocation, false, projectionMatrix.toFloatBuffer());
    }
}
