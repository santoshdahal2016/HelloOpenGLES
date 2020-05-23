package com.santosh.opengles.opengl;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.util.AttributeSet;

public class GLSurfaceViewOpengl extends GLSurfaceView {


    private  final GLRendererOpengl m_Renderer;
    /**
     * Standard View constructor. In order to render something, you
     * must call {@link #setRenderer} to register a renderer.
     *
     * @param context
     * @param attrs
     */
    public GLSurfaceViewOpengl(Context context, AttributeSet attrs) {
        super(context, attrs);


        // Create an OpenGL ES 3.0 context
        setEGLContextClientVersion(2);
        m_Renderer = new GLRendererOpengl(context);

        // Set the Renderer for drawing on the GLSurfaceView
        setRenderer(m_Renderer);
    }
}
