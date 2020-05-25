package com.santosh.opengles.opengl.element;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Model {

    private FloatBuffer vertexBuffer;
    private final int COORDS_PER_VERTEX = 3; // number of coordinates per vertex in this array
    private int vertexCount ;



    int[] vao = new int[1];
    int[] vbo = new int[1];


    public void loadData(float[] vertices ){


        vertexCount = vertices.length / COORDS_PER_VERTEX;

        // Move the Vertices data to the buffer

        ByteBuffer bb = ByteBuffer.allocateDirect(
                // (number of coordinate values * 4 bytes per float)
                vertices.length * 4);
        // use the device hardware's native byte order
        bb.order(ByteOrder.nativeOrder());

        // create a floating point buffer from the ByteBuffer

        vertexBuffer = bb.asFloatBuffer();
        // add the coordinates to the FloatBuffer
        vertexBuffer.put(vertices);
        // set the buffer to read the first coordinate
        vertexBuffer.position(0);


        GLES30.glGenVertexArrays(1, vao, 0);
        GLES30.glBindVertexArray(vao[0]);



        GLES30.glGenBuffers(1, vbo, 0);

        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER,vbo[0]);
        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER,vertices.length * 4,vertexBuffer,GLES30.GL_STATIC_DRAW);

        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, 0);


    }


    public void draw() {



        GLES30.glBindVertexArray(vao[0]);
        GLES30.glEnableVertexAttribArray(0);
        GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vertexCount);
        GLES20.glDisableVertexAttribArray(0);
        GLES30.glBindVertexArray(0);


    }
}
