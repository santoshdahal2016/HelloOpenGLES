package com.santosh.opengles.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;
import android.util.Log;

import com.santosh.opengles.helper.IOHelper;
import com.santosh.opengles.opengl.element.Model;
import com.santosh.opengles.opengl.element.Shader;


import java.util.Arrays;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class GLRendererOpengl implements GLSurfaceView.Renderer {


    float triangleCoords[] = {   // in counterclockwise order:
            0.0f,  0.622008459f, 0.0f, // top
            -0.5f, -0.311004243f, 0.0f, // bottom left
            0.5f, -0.311004243f, 0.0f  // bottom right
    };



    float pyramidsCoords[] = {

            //front face
            0.0f,  1.0f, 0.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,


            //right face
            0.0f,  1.0f, 0.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f,


            //back face
            0.0f,  1.0f, 0.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,

            //left face
            0.0f,  1.0f, 0.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
    };


    float cubeCoords[] = {

            //front face
            -1.0f,  1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f,  1.0f, 1.0f,
            1.0f,  1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,


            //right face
            1.0f,  1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f,  1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, -1.0f,


            //back face
            1.0f,  1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            -1.0f,  1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,

            -1.0f, -1.0f, -1.0f,

            //back face
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f,  1.0f, 1.0f,
            -1.0f,  1.0f, 1.0f,
            -1.0f, -1.0f, -1.0f,

            -1.0f, -1.0f, 1.0f,

    };



    float cubeCoordswithIdex[] = {

            //front face
            //x,    y,      z
            -1.0f,  1.0f, 1.0f,
            -1.0f, -1.0f, 1.0f,
            1.0f, -1.0f, 1.0f,
            1.0f,  1.0f, 1.0f,


            //x,    y,      z
            -1.0f,  1.0f, -1.0f,
            -1.0f, -1.0f, -1.0f,
            1.0f, -1.0f, -1.0f,
            1.0f,  1.0f, -1.0f,

    };


    int cobeIndex[] ={
            0,1,2,2,0,3,
            4,5,6,6,7,4,

            0,4,1,1,5,4,

            7,3,2,2,6,7
    };



    private Model m_Model;

    private Shader m_Shader;



    public GLRendererOpengl(Context context) {
        super();
        IOHelper.SetContext(context);

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0, 0, 0, 1);


        GLES20.glEnable(GLES20.GL_DEPTH_TEST);

        m_Shader = new Shader("shaders/phong.vert", "shaders/phong.frag");

        m_Model = new Model();

        m_Model.loadData(cubeCoordswithIdex, cobeIndex);

        m_Model.setScale(new float[]{0.25f,0.25f,0.25f});

        m_Model.setRotationZ(45);

        m_Model.setRotationY(55);

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }


    @Override
    public void onDrawFrame(GL10 gl) {

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT| GLES20.GL_DEPTH_BUFFER_BIT);

        m_Shader.useProgram();

        m_Model.incrementRotationZ(-1);

        m_Shader.setTransformation(m_Model.getModelMatrix());

        m_Model.draw();
    }
}
