package org.psigames.ssclient.handlers;

import org.lwjgl.glfw.GLFWKeyCallback;
import org.psigames.ssclient.Client;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.GL_TRUE;

/**
 * Created by jadengiordano on 8/26/15.
 */
public class KeyInputHandler {

    private GLFWKeyCallback keyCallback;

    public KeyInputHandler() {

        glfwSetKeyCallback(Client.singleton.window, keyCallback = new GLFWKeyCallback() {
            @Override
            public void invoke(long window, int key, int scancode, int action, int mods) {
                if ( key == GLFW_KEY_ESCAPE && action == GLFW_RELEASE )
                    glfwSetWindowShouldClose(window, GL_TRUE); // We will detect this in our rendering loop
            }
        });

    }

    public void setWindow(long window) {
        glfwSetKeyCallback(window, keyCallback);
    }

    public void release() {
        keyCallback.release();
    }

}
