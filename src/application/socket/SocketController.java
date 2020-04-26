/**
 * Copyright (c) 2020-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package application.socket;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import application.thread.PlayerServerThread;

/**
 * @author takunoji
 *
 * 2020/03/29
 */
public class SocketController {
	/** サーバーが起動するポート番号 */
	public final int PROCON_SERVER_PORT = 8888;
	/** プレーヤー数 */
	private int playerCount;
	/** ServerSocket */
	private ServerSocket server;
	
	/**
	 * コンストラクター。
	 * プロコンサーバーでのプレイヤー受付を行う。以下のクラスを使用する。
	 * 1. <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/net/ServerSocket.html">ServerSocket</a>: クライアントからのリクエストを受け付ける<br/>
	 * 2. <a href="https://docs.oracle.com/javase/jp/8/docs/api/java/net/Socket.html">Socket</a>: クライアントアプリのリクエスト<br/>
	 * <br/>
	 * <dl><dt>処理概要</dt>
	 * <dd>1. 8888バンポートを使用してこのサーバーを起動する</dd>
	 * <dd>2. </dd>
	 * </dl>
	 * @throws IOException 
	 */
	public SocketController() throws IOException {
		server = new ServerSocket(PROCON_SERVER_PORT);
		
	}

	/**
	 * プレーヤー受付処理の開始。
	 * 1. 受付を開始<br/>
	 * 2. 受付プレーヤー数が2以上になったらStartボタンを活性化する
	 * 3. スタートボタン押下まで、受付スレッドを待機させる
	 * 4. スタートボタン押下後に、
	 * @throws IOException 
	 */
	public void execute() throws IOException {
		while(true) {
			Socket access = server.accept();
			// 1アクセスにつきThreadを１つ作成する
			PlayerServerThread player = new PlayerServerThread(access);
			playerCount++;
		}
	}
	
}
