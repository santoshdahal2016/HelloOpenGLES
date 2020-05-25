package com.santosh.opengles.opengl.element;

import android.opengl.GLES20;


public class Shader {

     int m_pID;



    public Shader(String vs, String fs) {


        int m_vsID = GLES20.glCreateShader(GLES20.GL_VERTEX_SHADER);
        // add the source code to the shader and compile it
        GLES20.glShaderSource(m_vsID, vs);
        GLES20.glCompileShader(m_vsID);


        int m_fsID = GLES20.glCreateShader(GLES20.GL_FRAGMENT_SHADER);

        // add the source code to the shader and compile it
        GLES20.glShaderSource(m_fsID, fs);
        GLES20.glCompileShader(m_fsID);



        // create empty OpenGL ES Program
        m_pID = GLES20.glCreateProgram();

        // add the vertex shader to program
        GLES20.glAttachShader(m_pID, m_vsID);

        // add the fragment shader to program
        GLES20.glAttachShader(m_pID, m_fsID);






        // creates OpenGL ES program executables
        GLES20.glLinkProgram(m_pID);

    }


    public void useProgram() {

        GLES20.glUseProgram(m_pID);
    }


    public int getProgramID() {

        return m_pID;
    }




}
