package org.psigames.ssclient;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GLContext;
import org.psigames.ssclient.handlers.ErrorHandler;
import org.psigames.ssclient.handlers.KeyInputHandler;
import org.psigames.ssclient.handlers.WindowEventHandler;
import org.psigames.ssclient.tools.Constants;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.system.MemoryUtil.NULL;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by jadengiordano on 8/26/15.
 */
public class Client {

    // Singleton Definition
    public static Client singleton;

    public ErrorHandler errorHandler;
    public KeyInputHandler keyInputHandler;
    public WindowEventHandler windowEventHandler;
    public long window;

    private Game game;

    // Game Starts here
    public void run() {
        try {
            // Initialize Components mandatory for game function...
            if (!initEngineComponents()) {
                throw new RuntimeException("Failure to initialize engine components...");
            }

            // Main game loop
            loop();

            // Destroy Key Handler and Window
            glfwDestroyWindow(window);
            keyInputHandler.release();
        }
        finally {
            // Close GLFW and Error Handler
            glfwTerminate();
            errorHandler.release();
        }
    }

    private boolean initEngineComponents() {
        // Initialize Constants to be used in game for settings, file locations, etc.
        Constants.init();
        // Initialize Error Handler
        errorHandler = new ErrorHandler();
        // Initialize GLFW
        if ( glfwInit() != GL_TRUE )
            throw new IllegalStateException("Unable to initialize GLFW");
        // Create and Initialize Window
        createWindow();
        // Create Keyboard Input Handler
        keyInputHandler = new KeyInputHandler();
        return true;
    }

    private void createWindow() {
        // Configure Window
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable

        // Create window
        window = glfwCreateWindow(Constants.width, Constants.height, Constants.title, NULL, NULL);
        if ( window == NULL )
            throw new RuntimeException("Failed to create the GLFW window");

        // Create the Window/Window Callback (Resizing and such)
        windowEventHandler = new WindowEventHandler();

        // Make the OpenGL context current
        glfwMakeContextCurrent(window);

        // Enable v-sync
        glfwSwapInterval(1);

        // Make the window visible
        glfwShowWindow(window);
    }

    public void loop() {
        // Create the OpenGL context for rendering things
        GLContext.createFromCurrent();

        // Initialize OpenGL
        initGL();

        // Initialize Game
        this.game = new Game();

        // Run the rendering loop until the user has attempted to close
        // the window or has pressed the ESCAPE key.
        while ( glfwWindowShouldClose(window) == GL_FALSE ) {
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT); // clear the framebuffer

            game.update();
            game.render();

            glfwSwapBuffers(window); // swap the color buffers

            // Poll for window events.
            glfwPollEvents();
        }
    }

    private void initGL() {
        // Check is game can use VBO's
        String[] s = GL11.glGetString(GL11.GL_VERSION).split(" ");
        Constants.glVersion = Float.parseFloat(s[0]);

        // Initialize Viewport (Orthographic View/2D)
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Constants.width, Constants.height, 0, 10, -1);
        glMatrixMode(GL_MODELVIEW);

        // Set the clear color
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    public static void main(String[] args) {
        // Create the singleton
        singleton = new Client();
        singleton.run();
    }

}
