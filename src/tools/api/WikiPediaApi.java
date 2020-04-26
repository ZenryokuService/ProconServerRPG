/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONObject;

/**
 * WikiPediaAPIでのデータ取得クラス。
 * サンプル<br/>
 * https://ja.wikipedia.org/wiki?action=query&titles=San_Francisco&prop=images&imlimit=20&format=jsonfm
 * @author takunoji
 *
 * 2020/04/26
 */
public class WikiPediaApi {
	/** WikipediaのURL(エンドポイント) */
	public static String ROOT_URL = "http://ja.wikipedia.org/w/api.php?";
	public WikiPediaApi() {
		
	}

	public JSONObject getJSON(String search) throws Exception {
		String url = ROOT_URL + "action=query&titles=" + search;
		InputStream is = new URL(url).openStream();
		 try {
		      BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
		      String jsonText = readAll(rd);
		      JSONObject json = new JSONObject(jsonText);
		      return json;
		 } finally {
		      is.close();
		 }
	}

	private String readAll(Reader rd) throws IOException {
	  StringBuilder sb = new StringBuilder();
	  int cp;
	  while ((cp = rd.read()) != -1) {
	    sb.append((char) cp);
	  }
	  return sb.toString();
	}
}
