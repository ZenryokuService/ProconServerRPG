/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.lwjgl;

import org.lwjgl.glfw.GLFW;

import tools.lwjgl.window.Window;

/**
 * LWJGL3を利用するのに、テスト的に実行するクラス。
 * 
 * @see https://github.com/SilverTiger/lwjgl3-tutorial/wiki
 * @author takunoji
 * 2020/05/04
 */
public class GameMain {
	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;
	public static final int FPS = 60;

	public static void main(String[] args) {
		Window win = new Window(WIDTH, HEIGHT, FPS, "LWJGL Tutorial");
		win.create();
		// マウスのイベント設定
		win.setMouseHandler();
		win.setBackGroundColor(1.0f, 0.0f, 0.0f);

		//// ゲームループ ////
		while (!win.closed()) {
			if (win.isUpdating()) {
				win.update();
				if (win.isKeyPressed(GLFW.GLFW_KEY_A)) {
					System.out.println("Pressed Key A");
				}
				if (win.isKeyReleased(GLFW.GLFW_KEY_A)) {
					System.out.println("Released Key A");
				}
				win.swapBuffers();
			}
		}
	}
}
