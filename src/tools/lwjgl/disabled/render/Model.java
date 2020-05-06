/**
 * Copyright (c) 2019-present ProconServerRPG JavaFX Library All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without modification, are permitted provided that the following conditions are met:
 * Redistributions of source code must retain the above copyright notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following disclaimer in the documentation and/or other materials provided with the distribution.
 * Neither the name ProconServerRPG JavaFX Library nor the names of its contributors may be used to endorse or promote products derived from this software without specific prior written permission.
 */
package tools.lwjgl.disabled.render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

/**
 * @author takunoji
 *
 * 2020/05/05
 */
public class Model {
	private int vertexArrayID;
	private int vertexBufferID;
	private int vertexCount;
	private int indicesBufferID;
	private int[] indices;
	private float[] vertices;

	/**
	 * コンストラクタ
	 * @param vertices モデルの頂点
	 */
	public Model(float[] vertices, int[] indices) {
		this.vertices = vertices;
		this.indices = indices;
		vertexCount = vertices.length/3;
	}

	public void create() {
		FloatBuffer floatBuffer = BufferUtils.createFloatBuffer(vertices.length);
		floatBuffer.put(vertices);
		floatBuffer.flip();
		IntBuffer indicesBuffer = BufferUtils.createIntBuffer(vertices.length);
		indicesBuffer.put(indices);
		indicesBuffer.flip();
		
		vertexArrayID = GL30.glGenVertexArrays();
		GL30.glBindVertexArray(vertexArrayID);

		vertexBufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vertexBufferID);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, floatBuffer, GL15.GL_STATIC_DRAW);

		indicesBufferID = GL15.glGenBuffers();
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBufferID);
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL15.GL_STATIC_DRAW);
		
		GL20.glEnableVertexAttribArray(0);
		GL20.glVertexAttribPointer(0, 3, GL11.GL_FLOAT, false, 0, 0);
		GL30.glBindVertexArray(0);
		GL20.glDisableVertexAttribArray(0);
	}

	public void remove() {
		GL30.glDeleteVertexArrays(vertexArrayID);
		GL15.glDeleteBuffers(vertexBufferID);
		GL15.glDeleteBuffers(indicesBufferID);
	}

	/**
	 * @return the vertexArrayID
	 */
	public int getVertexArrayID() {
		return vertexArrayID;
	}

	/**
	 * @return the vertexBufferID
	 */
	public int getVertexBufferID() {
		return vertexBufferID;
	}

	/**
	 * @return the vertexCount
	 */
	public int getVertexCount() {
		return vertexCount;
	}
}
