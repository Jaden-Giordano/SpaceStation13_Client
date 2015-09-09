package org.psigames.ssclient.handlers;

import static org.lwjgl.glfw.GLFW.*;

import org.lwjgl.glfw.GLFWWindowSizeCallback;
import org.psigames.ssclient.Client;
import org.psigames.ssclient.tools.Constants;

/**
 * Created by jadengiordano on 8/26/15.
 */
public class WindowEventHandler {

    public GLFWWindowSizeCallback windowSizeCallback;

    public WindowEventHandler() { // Sets up glfw's keyCallback
        glfwSetWindowSizeCallback(Client.singleton.window, windowSizeCallback = new GLFWWindowSizeCallback() {

            public void invoke(long window, int width, int height) {
                Constants.width = width;
                Constants.height = height;
                //Client.singleton.resized(); Will be called when window resizing is a thing... (May not be a thing)
            }
        });
    }

    public void setWindow(long window) {
        glfwSetWindowSizeCallback(window, windowSizeCallback);
    }

    public void release() {
        windowSizeCallback.release();
    }

}
