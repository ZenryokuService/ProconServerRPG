/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.api;

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
		try {
			api.getJSON("");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
