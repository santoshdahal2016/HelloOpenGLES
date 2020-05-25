package com.santosh.opengles.opengl.element;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.util.Log;

import java.util.Arrays;


public class Shader {

     int m_pID;


     int transformationMatrix;

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

        transformationMatrix = GLES30.glGetUniformLocation(m_pID, "transformationMatrix");




        int[] status = new int[2];
        GLES30.glGetIntegerv(GLES30.GL_MAJOR_VERSION, status, 0);
        GLES30.glGetIntegerv(GLES30.GL_MINOR_VERSION, status, 1);
        Log.d("Shader", "GLES version "  + String.valueOf(status[0]) + "." + String.valueOf(status[1]));
        GLES30.glGetIntegerv(GLES30.GL_SHADING_LANGUAGE_VERSION, status, 0);
        Log.d("Shader", "Shader version "  + String.valueOf(status[0]));
        GLES30.glGetProgramiv(m_pID, GLES30.GL_LINK_STATUS, status, 0);
        if (status[0] == 0) {
            Log.d("Shader", "Program InfoLog: " + GLES30.glGetProgramInfoLog(m_pID));
        }
        Log.d("Shader", "Link Status: " + String.valueOf(status[0]));
        GLES30.glGetShaderiv(m_vsID, GLES30.GL_COMPILE_STATUS, status, 0);
        Log.d("Shader", "Vertex Shader Compile Status: " + String.valueOf(status[0]));
        if (status[0] == 0) {
            Log.d("Shader", GLES30.glGetShaderInfoLog(m_vsID));
        }
        GLES30.glGetShaderiv(m_fsID, GLES30.GL_COMPILE_STATUS, status, 0);
        Log.d("Shader", "Fragment Shader Compile Status: " + String.valueOf(status[0]));
        if (status[0] == 0) {
            Log.d("Shader", GLES30.glGetShaderInfoLog(m_fsID));
        }

    }


    public void useProgram() {

        GLES30.glUseProgram(m_pID);
    }


    public int getProgramID() {

        return m_pID;
    }


    public void setTransformation(float[] TM) {



        GLES30.glUniformMatrix4fv(transformationMatrix, 1, false, TM, 0);
    }

}
