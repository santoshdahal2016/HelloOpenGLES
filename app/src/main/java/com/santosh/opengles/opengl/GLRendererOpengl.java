package com.santosh.opengles.opengl;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

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


    // Define vertex Shader Code

    final String vertexShaderCode =
            "attribute vec4 vPosition;" +
                    "void main() {" +
                    "  gl_Position = vPosition;" +
                    "}";

    // Define fragment Shader Code

    final String fragmentShaderCode =
            "precision mediump float;" +
                    "uniform vec4 vColor;" +
                    "void main() {" +
                    "  gl_FragColor = vColor;" +
                    "}";



    private Model m_Model;

    private Shader m_Shader;



    public GLRendererOpengl(Context context) {
        super();



    }


    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        GLES20.glClearColor(0f, 0, 0, 1);

        m_Shader = new Shader(vertexShaderCode,fragmentShaderCode);

        m_Model = new Model();

        m_Model.loadData(triangleCoords , m_Shader);

    }


    @Override
    public void onSurfaceChanged(GL10 gl, int width, int height) {

    }


    @Override
    public void onDrawFrame(GL10 gl) {

        GLES20.glClear(GLES20.GL_COLOR_BUFFER_BIT);

        m_Model.draw();
    }
}
