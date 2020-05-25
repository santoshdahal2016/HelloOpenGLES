package com.santosh.opengles.opengl.element;

import android.opengl.GLES20;
import android.opengl.GLES30;


public class Shader {

     int m_pID;


    public Shader(String vs, String fs) {



        int m_vsID = GLES30.glCreateShader(GLES30.GL_VERTEX_SHADER);
        GLES30.glShaderSource(m_vsID, vs);
        GLES30.glCompileShader(m_vsID);

        int m_fsID = GLES30.glCreateShader(GLES30.GL_FRAGMENT_SHADER);
        GLES30.glShaderSource(m_fsID, fs);
        GLES30.glCompileShader(m_fsID);

        m_pID = GLES30.glCreateProgram();
        GLES30.glAttachShader(m_pID, m_vsID);
        GLES30.glAttachShader(m_pID, m_fsID);

        GLES30.glBindAttribLocation(m_pID, 0, "vPosition");


        GLES30.glLinkProgram(m_pID);


    }


    public void useProgram() {

        GLES30.glUseProgram(m_pID);
    }


    public int getProgramID() {

        return m_pID;
    }




}
