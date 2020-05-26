package com.santosh.opengles.opengl.element;

import android.opengl.GLES20;
import android.opengl.GLES30;
import android.opengl.Matrix;
import android.util.Log;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public class Model {

    private FloatBuffer vertexBuffer;

    private IntBuffer indexBuffer;


    private int vertexCount ;
    private int indexCount;

    protected float[] position = new float[] { 0f, 0f, 0f };
    protected float[] rotation = new float[] { 0f, 0f, 0f };
    protected float[] scale = new float[] { 1, 1, 1 };
    protected float[] bindShapeMatrix = new float[16];

    protected float[] modelMatrix = new float[16];
    protected float[] newModelMatrix = new float[16];
    {
        //
        Matrix.setIdentityM(modelMatrix,0);
        Matrix.setIdentityM(bindShapeMatrix,0);
        Matrix.setIdentityM(newModelMatrix,0);
    }



    int[] vao = new int[1];
    int[] vbo = new int[2];


    public void loadData(float[] vertices , int[] index ){


        indexCount = index.length;
        // number of coordinates per vertex in this array
        int COORDS_PER_VERTEX = 3;
        vertexCount = vertices.length / COORDS_PER_VERTEX;

        // Creating VAO
        GLES30.glGenVertexArrays(1, vao, 0);
        GLES30.glBindVertexArray(vao[0]);


        //Vertices Position Buffer

        GLES30.glGenBuffers(2, vbo, 0);




        GLES30.glBindBuffer(GLES30.GL_ARRAY_BUFFER,vbo[0]);

        vertexBuffer = FloatBuffer.allocate(vertices.length);
        vertexBuffer.put(vertices);
        vertexBuffer.position(0);

        GLES30.glBufferData(GLES30.GL_ARRAY_BUFFER,vertices.length * 4,vertexBuffer,GLES30.GL_STATIC_DRAW);


        GLES30.glEnableVertexAttribArray(0);
        GLES30.glVertexAttribPointer(0, 3, GLES30.GL_FLOAT, false, 0, 0);




        //Index Buffer
        GLES30.glBindBuffer(GLES30.GL_ELEMENT_ARRAY_BUFFER,vbo[1]);

        indexBuffer = IntBuffer.allocate(index.length);
        indexBuffer.put(index);
        indexBuffer.position(0);

        GLES30.glBufferData(GLES30.GL_ELEMENT_ARRAY_BUFFER,index.length * 4,indexBuffer,GLES30.GL_STATIC_DRAW);

    }


    public void draw() {



        GLES30.glBindVertexArray(vao[0]);
        GLES30.glEnableVertexAttribArray(0);
        GLES20.glDrawElements(GLES20.GL_TRIANGLES, indexCount, GLES30.GL_UNSIGNED_INT, 0);
        GLES20.glDisableVertexAttribArray(0);
        GLES30.glBindVertexArray(0);


    }



    public void setScale(float[] scale){
        this.scale = scale;
        updateModelMatrix();
    }


    public void setRotationY(float rotY) {
        this.rotation[1] = rotY;
        updateModelMatrix();
    }


    public void setRotationZ(float rotZ) {
        this.rotation[2] = rotZ;
        updateModelMatrix();
    }

    public void incrementRotationZ(float rotZ) {
        this.rotation[2] =  this.rotation[2]+rotZ;
        updateModelMatrix();
    }

    public void setRotationX(float rotX) {
        this.rotation[0] = rotX;
        updateModelMatrix();
    }

    protected void updateModelMatrix(){
        Matrix.setIdentityM(modelMatrix, 0);
        if (getRotation() != null) {
            Matrix.rotateM(modelMatrix, 0, getRotation()[0], 1f, 0f, 0f);
            Matrix.rotateM(modelMatrix, 0, getRotation()[1], 0, 1f, 0f);
            Matrix.rotateM(modelMatrix, 0, getRotationZ(), 0, 0, 1f);
        }
        if (getScale() != null) {
            Matrix.scaleM(modelMatrix, 0, getScaleX(), getScaleY(), getScaleZ());
        }
        if (getPosition() != null) {
            Matrix.translateM(modelMatrix, 0, getPositionX(), getPositionY(), getPositionZ());
        }
        if (this.bindShapeMatrix == null){
            // geometries not linked to any joint does not have bind shape transform
            System.arraycopy(this.modelMatrix,0,this.newModelMatrix,0,16);
        } else {
            Matrix.multiplyMM(newModelMatrix, 0, this.modelMatrix, 0, this.bindShapeMatrix, 0);
        }
    }


    public float[] getRotation() {
        return rotation;
    }

    public float getRotationX(){
        return rotation[0];
    }

    public float getRotationY(){
        return rotation[1];
    }

    public float getRotationZ() {
        return rotation[2];
    }


    public float[] getScale(){
        return scale;
    }

    public float getScaleX() {
        return getScale()[0];
    }

    public float getScaleY() {
        return getScale()[1];
    }

    public float getScaleZ() {
        return getScale()[2];
    }



    public float[] getPosition() {
        return position;
    }

    public float getPositionX() {
        return position != null ? position[0] : 0;
    }

    public float getPositionY() {
        return position != null ? position[1] : 0;
    }

    public float getPositionZ() {
        return position != null ? position[2] : 0;
    }


    public float[] getModelMatrix() {
        return modelMatrix;
    }
}
