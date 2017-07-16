package code.graphics;

import code.util.ShaderUtils;

import static org.lwjgl.opengl.GL20.glUseProgram;

/**
 * Created by theod on 16-7-2017.
 */
public class ShaderProgram {
    protected int program;

    public ShaderProgram(String vertexShader, String fragmentShader){
        program = ShaderUtils.load(vertexShader, fragmentShader);
    }

    public void enable(){
        glUseProgram(program);
    }

    public void disable(){
        glUseProgram(0);
    }
}
