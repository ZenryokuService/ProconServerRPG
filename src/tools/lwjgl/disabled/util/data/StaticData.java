/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.lwjgl.disabled.util.data;

import tools.lwjgl.disabled.render.Model;

/**
 * @author takunoji
 *
 * 2020/05/05
 */
public class StaticData {
	public static Model getTriangle() {
		return new Model(new float[] {
				-1.0f, -1.0f, 0.0f,
				 0.0f, 1.0f, 0.0f,
				1.0f, -1.0f, 0.0f
		}, new int[] {
				
		});
	}
	
	public static Model getTriangle2() {
		return new Model(new float[] {
				-0.5f, 0.5f, 0.0f,
				 0.5f, 0.5f, 0.0f,
				-0.5f, -0.5f, 0.0f,
				 0.5f, -0.5f, 0.0f,
		}, new int[] {
				0, 1, 2,
				2, 3, 1
		});
	}
}
