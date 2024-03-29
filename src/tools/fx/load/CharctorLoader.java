/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.fx.load;

import java.io.File;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.stage.Stage;

/**
 * @author takunoji
 *
 * 2020/04/27
 */
public class CharctorLoader {

	/** ３Dモデルのパス */
	private String modelUrl;

	/**
	 * 作成した3Dモデルをロード、インスタンスを保持する。
	 * は池部分(ステージ)を透明化してキャラクターがPC上で動いているように見せる<br/>
	 * 
	 * @param res 3dモデルのファイルパス
	 */
	public CharctorLoader(String res) {
		this.modelUrl = res;
	}

	public void start() {
		
	}
	public static void main(String[] args) {
		
	}
}
