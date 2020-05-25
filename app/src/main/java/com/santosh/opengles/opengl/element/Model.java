package com.santosh.opengles.opengl.element;

import android.opengl.GLES20;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

public class Model {

    private FloatBuffer vertexBuffer;
    private final int COORDS_PER_VERTEX = 3; // number of coordinates per vertex in this array
    private int vertexCount ;


    private Shader m_Shader;



    public void loadData(float[] vertices , Shader shader){

        m_Shader = shader;

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




    }


    public void draw() {


        m_Shader.useProgram();



       int m_colorHandle = GLES20.glGetUniformLocation(m_Shader.getProgramID(), "vColor");


        GLES20.glEnableVertexAttribArray(0);

        int vertexStride = COORDS_PER_VERTEX * 4;

        GLES20.glVertexAttribPointer(0, COORDS_PER_VERTEX,
                GLES20.GL_FLOAT, false,
                vertexStride, vertexBuffer);


        // Set color for drawing the triangle
        float color[] = { 0.63671875f, 0.76953125f, 0.22265625f, 1.0f };
        GLES20.glUniform4fv(m_colorHandle, 1, color, 0);


        // Draw the triangle
        GLES20.glDrawArrays(GLES20.GL_TRIANGLE_FAN, 0, vertexCount);

        // Disable vertex array
        GLES20.glDisableVertexAttribArray(0);
    }
}
