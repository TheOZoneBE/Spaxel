package code.graphics;

import code.Game;
import org.lwjgl.opengl.GL30;

import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.*;
import static org.lwjgl.opengl.GL32.glFramebufferTexture;


/**
 * Created by theod on 16-7-2017.
 */
public class FBO {
    private int fbo;
    private int texture;
    private int depthBuffer;

    public FBO(){
        fbo = glGenFramebuffers();
        glBindFramebuffer(GL_FRAMEBUFFER, fbo);
        glDrawBuffer(GL_COLOR_ATTACHMENT0);
        texture = glGenTextures();
        glBindTexture(GL_TEXTURE_2D, texture);
        glTexImage2D(GL_TEXTURE_2D, 0, GL_RGB, Game.GAME_WIDTH, Game.GAME_HEIGHT,
                0, GL_RGB, GL_UNSIGNED_BYTE, (ByteBuffer) null);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
        glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
        glFramebufferTexture(GL30.GL_FRAMEBUFFER, GL30.GL_COLOR_ATTACHMENT0,
                texture, 0);
        glBindTexture(GL_TEXTURE_2D, 0);

        depthBuffer =glGenRenderbuffers();
        glBindRenderbuffer(GL30.GL_RENDERBUFFER, depthBuffer);
        glRenderbufferStorage(GL30.GL_RENDERBUFFER, GL_DEPTH_COMPONENT, Game.GAME_WIDTH,
                Game.GAME_HEIGHT);
        GL30.glFramebufferRenderbuffer(GL30.GL_FRAMEBUFFER, GL30.GL_DEPTH_ATTACHMENT,
                GL30.GL_RENDERBUFFER, depthBuffer);

        glBindFramebuffer(GL_FRAMEBUFFER, 0);
    }

    public void bindBuffer(){
        glBindTexture(GL_TEXTURE_2D, 0);
        GL30.glBindFramebuffer(GL30.GL_FRAMEBUFFER, fbo);
        glViewport(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }

    public void unbindBuffer(){
        glBindFramebuffer(GL_FRAMEBUFFER, 0);
        glViewport(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
    }

    public void bindTexture(){
        glBindTexture(GL_TEXTURE_2D, texture);
    }

    public void unbindTexture(){
        glBindTexture(GL_TEXTURE_2D, 0);
    }

    public void bindDepth(){
        glBindRenderbuffer(GL_RENDERBUFFER, depthBuffer);
    }

    public void unbindDepth(){
        glBindRenderbuffer(GL_RENDERBUFFER, 0);
    }
}
