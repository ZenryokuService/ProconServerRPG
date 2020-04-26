/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package application.thread;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

import application.ProconServer;

/**
 * プロコンサーバーでのプレーヤースレッド。
 * プレーヤーアクセス時の処理を実装する。
 * 
 * @author takunoji
 * 2020/03/29
 */
public class PlayerServerThread extends Thread {
	/** アクセスしているSocket */
	private Socket access;
	
	/**
	 * プレーヤーアクセス１つにつき１つのスレッドを立ち上げる。
	 * 
	 * @param playerAccess
	 */
	public PlayerServerThread(Socket playerAccess) {
		access = playerAccess;
	}

	/**
	 * コンストラクター実行前(接続する前)に、アクセスキーのチェックを行う。
	 * @param playerAccess 取得したソケット
	 * @return boolean: アクセスキーが正しい時にtrueを返す
	 */
	public static boolean isPlayer(Socket playerAccess) {
		try {
			isPlayer(playerAccess.getInputStream());
		} catch (IOException e) {
			return false;
		}
		return true;
	}

	/**
	 * クライアントアプリからのアクセスキーを取得、判定する。
	 * アクセスキーは、プロコンサーバーのJavaDocに記載している。
	 * @param input Socketより取り出したInputStream
	 * @return playerアクセスの時にtrue
	 * @throws IOException 
	 */
	private static boolean isPlayer(InputStream input) throws IOException {
		// アクセスキー分のデータを読み込む
		InputStreamReader read = new InputStreamReader(input, "utf-8");
		int c;
		char[] key = new char[ProconServer.ACCESS_KEY.length()];
		int keyCount = 0;
		while((c = read.read()) != -1) {
			if (keyCount > ProconServer.ACCESS_KEY.length()) {
				throw new IOException("不適切なアクセスキーです。アクセスキーを見直してください。");
			}
			key[keyCount] = (char)c;
			keyCount++;
		}

		byte[] readLength = new byte[ProconServer.ACCESS_KEY.getBytes().length];
		input.read(readLength);
		return true;
	}

	/**
	 * スレッドが走った時の処理
	 */
	@Override
	public void run() {
		
	}
}
