package code.graphics.shaders;

import code.math.VectorF;

import static org.lwjgl.opengl.GL20.*;

/**
 * Created by theod on 25-7-2017.
 */
public class BlurShaderProgram extends ShaderProgram {
    private int texSamplerLocation;
    private int radiusLocation;
    private int resolutionLocation;
    private int sizeLocation;
    private int dirLocation;

    public BlurShaderProgram(String vertexShader, String fragmentShader) {
        super(vertexShader, fragmentShader);
        texSamplerLocation = glGetUniformLocation(program, "tex_sampler");
        radiusLocation = glGetUniformLocation(program, "radius");
        resolutionLocation = glGetUniformLocation(program, "resolution");
        sizeLocation = glGetUniformLocation(program, "size");
        dirLocation = glGetUniformLocation(program, "dir");
    }

    public void setTexSampler(int sampler){
        glUniform1i(texSamplerLocation, sampler);
    }

    public void setRadius(float radius){
        glUniform1f(radiusLocation, radius);
    }

    public void setResolution(float resolution){
        glUniform1f(resolutionLocation, resolution);
    }

    public void setSize(int size){
        glUniform1i(sizeLocation, size);
    }

    public void setDir(VectorF dir){
        glUniform2f(dirLocation, dir.getValue(0), dir.getValue(1));
    }
}
