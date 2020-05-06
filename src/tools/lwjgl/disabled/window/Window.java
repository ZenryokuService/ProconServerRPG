/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.lwjgl.disabled.window;

import java.nio.DoubleBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWMouseButtonCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;

/**
 * @author takunoji
 *
 * 2020/05/04
 */
public class Window {
	/** 画面の幅 */
	private int width;
	/** 画面の高さ */
	private int height;
	/** 画面のタイトル */
	private String title;
	/** 画面の(プログラムレベルの)番号 */
	private long window;
	/** 背景 */
	private Vector3f backgroundColor;

	private double fps_cap;
	private double time;
	private double processedTime = 0;

	/** キーボート配列の数だけ配列を用意 */
	private boolean[] keys = new boolean[GLFW.GLFW_KEY_LAST];
	/** マウスのボタン数だけ配列を用意 */
	private boolean[] mouseButtons = new boolean[GLFW.GLFW_MOUSE_BUTTON_LAST];

	/**
	 * 画面を作成する、コンストラクター。
	 * 
	 * @param width 画面の幅
	 * @param height 画面の高さ
	 * @param title 画面のタイトル
	 */
	public Window(int width, int height, int fps, String title) {
		this.width = width;
		this.height = height;
		this.title = title;
		this.fps_cap = fps;
		// 背景は黒で初期化
		this.backgroundColor = new Vector3f(0.0f, 0.0f, 0.0f);
	}

	/**
	 * 画面を作成する処理。
	 */
	public void create() {
		if (!GLFW.glfwInit()) {
			System.err.println("Error: Coudn not GLFW");
			System.exit(-1);
		}
		window = GLFW.glfwCreateWindow(width, height, title, 0, 0);
		if (window == 0) {
			System.err.println("Error: window coud not be created");
			System.exit(-1);
		}
		GLFW.glfwMakeContextCurrent(window);
		GL.createCapabilities();

		GLFWVidMode videoMode = GLFW.glfwGetVideoMode(GLFW.glfwGetPrimaryMonitor());
		GLFW.glfwSetWindowPos(window, (videoMode.width() - width) / 2, (videoMode.height() - height) / 2);
		
		this.time = getTime();
	}

	/** 画面を閉じる */
	public boolean closed() {
		return GLFW.glfwWindowShouldClose(window);
	}

	/** 画面を更新する */
	public void update() {
//		GL11.clear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);
		for (int i = 0; i < GLFW.GLFW_KEY_LAST; i++) keys[i] = isKeyDown(i);
//	動かない	for (int i = 0; i < GLFW.GLFW_MOUSE_BUTTON_LAST; i++) keys[i] = isMouseDown(i);
		GL11.glClearColor(backgroundColor.x, backgroundColor.y, backgroundColor.z, 1.0f);
		GL11.glClear(GL11.GL_COLOR_BUFFER_BIT);
		GLFW.glfwPollEvents();
	}

	public boolean isUpdating() {
		double nextTime = getTime();
		double passedTime = nextTime - time;
		processedTime += passedTime;
		time = nextTime;

		while(processedTime > 1.0/fps_cap) {
			processedTime -= 1.0/fps_cap;
			return true;
		}
		return false;
	}
	
	public void swapBuffers() {
		GLFW.glfwSwapBuffers(window);
	}

	public double getTime() {
		// このままだとintで帰って来るのでキャストする
		return (double) System.nanoTime() / 100000000;
	}

	public void stop() {
		GLFW.glfwTerminate();
	}
	
	/** 
	 * キーボートの入力を受付
	 * @param keyCode this{@link #keys}の配列番号
	 * @return キーボードが押下されているか判定した結果
	 */
	public boolean isKeyDown(int keyCode) {
		return GLFW.glfwGetKey(window, keyCode) == 1;
	}

	/** 
	 * マウスの入力を受付
	 * @param mouseButton this{@link #mouseButtons}の配列番号
	 * @return マウスのボタンが押されているか判定した結果
	 */
	@Deprecated
	public boolean isMouseDown(int mouseButton) {
		return GLFW.glfwGetMouseButton(window, mouseButton) == 1;
	}

	/** キーボードのボタンが押下されているか判定 */
	public boolean isKeyPressed(int keyCode) {
		return isKeyDown(keyCode) && !keys[keyCode];
	}

	/** キーボードのボタンが離されているか判定 */
	public boolean isKeyReleased(int keyCode) {
		return isKeyDown(keyCode) && keys[keyCode];
	}

	/** マウスのボタンが押下されているか判定 */
	@Deprecated
	public boolean isMouseButtonPressed(int mouseButton) {
		return isKeyDown(mouseButton) && !mouseButtons[mouseButton];
	}

	/** マウスのボタンが離されているか判定 */
	public boolean isMouseButtonReleased(int mouseButton) {
		return isKeyDown(mouseButton) && mouseButtons[mouseButton];
	}

	/** マウスのX軸座標取得 */
	@Deprecated
	public double getMouseX() {
		DoubleBuffer buf = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(window, buf, null);
		return buf.get(0);
	}

	/** マウスのY軸座標取得 */
	@Deprecated
	public double getMouseY() {
		DoubleBuffer buf = BufferUtils.createDoubleBuffer(1);
		GLFW.glfwGetCursorPos(window, null, buf);
		return buf.get(0);
	}

	public void setMouseHandler() {
		GLFWMouseButtonCallback mouseCallback = null;
		GLFW.glfwSetMouseButtonCallback(window, mouseCallback = GLFWMouseButtonCallback.create((window, button, action, mods) -> {
			if (button == 0) {
				// 左のクリック
				System.out.println("クリック");
			}
			if (button == 1) {
				// 左のクリック
				System.out.println("右クリック");
			}
			if (button == 2) {
				// ホイールのクリック
				System.out.println("ホイールクリック");
			}
		}));
	}

	//// ------ Getters & Setters ------ ////
	
	/**
	 * @return the width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * @return the height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @return the window
	 */
	public long getWindow() {
		return window;
	}

	/**
	 * @return the fps_cap
	 */
	public double getFps() {
		return fps_cap;
	}

	/**
	 * @return the processedTime
	 */
	public double getProcessedTime() {
		return processedTime;
	}

	/**
	 * @return the keys
	 */
	public boolean[] getKeys() {
		return keys;
	}

	/**
	 * @return the mouseButtons
	 */
	public boolean[] getMouseButtons() {
		return mouseButtons;
	}

	public void setBackGroundColor(float red, float green, float blue) {
		this.backgroundColor = new Vector3f(red, green, blue);
	}
}
