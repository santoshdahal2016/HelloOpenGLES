package com.santosh.opengles.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.util.Log;

import com.santosh.opengles.opengl.element.Model;
import com.santosh.opengles.opengl.element.Shader;


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

            //back face
            0.0f,  1.0f, 0.0f,
            -1.0f, -1.0f, -1.0f,
            -1.0f, -1.0f, 1.0f,
    };


    // Define vertex Shader Code

    final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    // Define fragment Shader Code

    final String fragmentShaderCode =
            "precision mediump float;" +
                    "void main() {" +
                    "  gl_FragColor = vec4(0.0f,1.0f,0.0f,1.0f);" +
                    "}";



    private Model m_Model;

    private Shader m_Shader;



    public GLRendererOpengl(Context context) {
        super();

    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(1f, 0, 0, 1);

        m_Shader = new Shader(vertexShaderCode,fragmentShaderCode);

        m_Model = new Model();

        m_Model.loadData(triangleCoords);

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }


    @Override
    public void onDrawFrame(GL10 gl) {

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        m_Shader.useProgram();

        m_Model.draw();
    }
}
