/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.lwjgl;

import org.junit.Test;
import org.lwjglb.engine.Utils;

import tools.lwjgl.disabled.OBJLoader;

/**
 * @author takunoji
 *
 * 2020/05/04
 */
public class ObjectLoaderTest {

	@Test
	public void test01() {
		try {
			System.out.println(Utils.loadResource("/shaders/depth_vertex.vs"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
