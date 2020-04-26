/**
 * Copyright (c) 2020-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package application;

import application.socket.SocketController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;

/**
 * プロコンRPGサーバーの画面表示を行う。
 * <ol>以下のファイルから起動可能とする。
 * <li>ProconServer.bat(for Windows)/li>
 * <li>ProconServer.sh(for Mac or Linux)</li>
 * </ol>
 * 
 * <dl><dt>処理概要</dt>
 * <dd>1. JavaFXでの画面表示でプレイヤーの受付を行う</dd>
 * <dd>2. Player1〜4までのプレイヤーは2001〜2004番までのポートに接続可能</dd>
 * <dd>3. クライアントアプリからの通信によりプレイヤーを生成する(詳細は別途記載)</dd>
 * <dd>4. Socket通信によりクライアントとデータ通信を行う(データフォーマットは別途記載)</dd>
 * </dl>
 * 
 * <h3>アクセスキー: Let it be</h3>
 * @see SoketController
 */
public class ProconServer extends Application {
	/** アクセスキー */
	public static final String ACCESS_KEY = "LetItBe";
	private SocketController serverSocket;

	@Override
	public void start(Stage primaryStage) throws Exception {
		// FXMLのロード
		Parent baseNode  = FXMLLoader.load(getClass().getResource("/ProconServer.fxml"));
		Scene scene = new Scene(baseNode);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();

		// サーバーソケット作成
		serverSocket = new SocketController();
		// プレーヤー受付開始
		serverSocket.execute();
		
	}
	
	public static void main(String[] args) {
		try {
			launch(args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
