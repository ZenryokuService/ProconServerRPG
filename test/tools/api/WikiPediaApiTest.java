/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.api;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author takunoji
 *
 * 2020/04/26
 */
public class WikiPediaApiTest {
	private WikiPediaApi api = null;
	
	@Before
	public void init() {
		api = new WikiPediaApi();
	}
	
	@After
	public void terminated() {
		api = null;
	}

	@Test
	public void testDownloadJSON() {
		JSONArray res = null;
		try {
			res = api.getJSON("town");
		} catch (Exception e) {
			e.printStackTrace();
		}
		res.forEach(System.out::println);
	}
	private void showKeysInJSONObject(JSONObject obj) {
		Iterator<String> it = obj.keys();
		while(it.hasNext()) {
			System.out.println("value: " + it.next());
		}
	}
}
