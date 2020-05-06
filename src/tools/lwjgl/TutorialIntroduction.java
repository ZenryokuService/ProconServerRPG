/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.lwjgl;

import static org.lwjgl.system.MemoryUtil.NULL;

import java.nio.IntBuffer;

import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.system.MemoryUtil;

/**
 * 
 * @author takunoji
 * @see https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Introduction
 * 2020/05/05
 */
public class TutorialIntroduction {
	/** ウィンドウのID？ */
    private long window;
	/** This error callback will simply print the error to */
    private GLFWErrorCallback errorCallback;

    /** This key callback will check if ESC is pressed and will close the window */
    private GLFWKeyCallback keyCallback;

	/**
	 * コンストラクタ。
	 * 画面の初期化処理を行う<br/>
	 * 1. コールバック関数の設定<br/>
	 * 2. GLFW(画面のコントロールクラス)の初期化<br/>
	 * 3. Windowの作成、表示位置の指定<br/>
	 * 4. 
	 */
	public TutorialIntroduction() {
		createCallBacks();
        /* Initialize GLFW */
        if (!GLFW.glfwInit()) {
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        /* Create window */
        window = GLFW.glfwCreateWindow(640, 480, "Simple example", NULL, NULL);
        if (window == NULL) {
        	GLFW.glfwTerminate();
            throw new RuntimeException("Failed to create the GLFW window");
        }

        /* Center the window on screen */
        GLFWVidMode vidMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
        GLFW.glfwSetWindowPos(window,
                         (vidMode.width() - 640) / 2,
                         (vidMode.height() - 480) / 2
        );

        /* Create OpenGL context */
    	GLFW.glfwMakeContextCurrent(this.getWindow());
        GL.createCapabilities();

        /* Enable vertical synchronization */
        GLFW.glfwSwapInterval(1);

        /* Set the key callback */
        GLFW.glfwSetKeyCallback(this.getWindow(), this.getKeyCallback());
	}

	private void createCallBacks() {
		errorCallback = GLFWErrorCallback.createPrint(System.err);
		keyCallback = new GLFWKeyCallback() {
	        @Override
	        public void invoke(long window, int key, int scancode, int action, int mods) {
	            if (key == GLFW.GLFW_KEY_ESCAPE && action == GLFW.GLFW_PRESS) {
	            	GLFW.glfwSetWindowShouldClose(window, true);
	            }
	        }
	    };
        /* Set the error callback */
        GLFW.glfwSetErrorCallback(this.getErrorCallback());

	}

	public void terminated(IntBuffer width, IntBuffer height, TutorialIntroduction main) {
        /* Free buffers */
        MemoryUtil.memFree(width);
        MemoryUtil.memFree(height);

        /* Release window and its callbacks */
        GLFW.glfwDestroyWindow(main.getWindow());
        main.getKeyCallback().free();

        /* Terminate GLFW and release the error callback */
        GLFW.glfwTerminate();
        main.getErrorCallback().free();
	}

	public void render() {
        /* Render triangle */
        GL11.glBegin(GL11.GL_TRIANGLES);
        GL11.glColor3f(1f, 0f, 0f);
        GL11.glVertex3f(-0.6f, -0.4f, 0f);
        GL11.glColor3f(0f, 1f, 0f);
        GL11.glVertex3f(0.6f, -0.4f, 0f);
        GL11.glColor3f(0f, 0f, 1f);
        GL11.glVertex3f(0f, 0.6f, 0f);
        GL11.glEnd();
	}

	public void rederingTriangle() {
		
	}
	
	/// ---- Getters & Setetrs ---- ///
	/**
	 * @return the window
	 */
	public long getWindow() {
		return window;
	}


	/**
	 * @param window the window to set
	 */
	public void setWindow(long window) {
		this.window = window;
	}


	/**
	 * @return the errorCallback
	 */
	public GLFWErrorCallback getErrorCallback() {
		return errorCallback;
	}


	/**
	 * @param errorCallback the errorCallback to set
	 */
	public void setErrorCallback(GLFWErrorCallback errorCallback) {
		this.errorCallback = errorCallback;
	}


	/**
	 * @return the keyCallback
	 */
	public GLFWKeyCallback getKeyCallback() {
		return keyCallback;
	}


	/**
	 * @param keyCallback the keyCallback to set
	 */
	public void setKeyCallback(GLFWKeyCallback keyCallback) {
		this.keyCallback = keyCallback;
	}


	/**
	 * LWJGL3でやるべきこと。
	 * 1. Initializing the OpenGL context
	 * 2. Creating a window
	 * 3. Update and Rendering loop
	 * 4. Ending the Application
	 * @see https://github.com/SilverTiger/lwjgl3-tutorial/wiki/Introduction
	 * @param args command argments
	 */
    public static void main(String[] args) {
    	TutorialIntroduction main = new TutorialIntroduction();

        /* Declare buffers for using inside the loop */
        IntBuffer width = MemoryUtil.memAllocInt(1);
        IntBuffer height = MemoryUtil.memAllocInt(1);

        /* Loop until window gets closed */
        while (!GLFW.glfwWindowShouldClose(main.getWindow())) {
            float ratio;

            /* Get width and height to calcualte the ratio */
            GLFW.glfwGetFramebufferSize(main.getWindow(), width, height);
            ratio = width.get() / (float) height.get();

            /* Rewind buffers for next get */
            width.rewind();
            height.rewind();

            /* Set viewport and clear screen */
            GL11.glViewport(0, 0, width.get(), height.get());
            GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);

            /* Set ortographic projection */
            GL11.glMatrixMode(GL11.GL_PROJECTION);
            GL11.glLoadIdentity();
            GL11.glOrtho(-ratio, ratio, -1f, 1f, 1f, -1f);
            GL11.glMatrixMode(GL11.GL_MODELVIEW);

            /* Rotate matrix */
//            GL11.glLoadIdentity();
//            GL11.glRotatef((float) GLFW.glfwGetTime() * 50f, 0f, 0f, 1f);

            /* render */
            main.render();
            /* Swap buffers and poll Events */
            GLFW.glfwSwapBuffers(main.getWindow());
            GLFW.glfwPollEvents();

            /* Flip buffers for next loop */
            width.flip();
            height.flip();
        }
        main.terminated(width, height, main);
    }
}
