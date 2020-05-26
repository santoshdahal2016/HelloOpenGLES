#version 300 es

in vec3 vPosition;


uniform mat4 transformationMatrix;

out vec3 outcolourValue;

void main() {

gl_Position =  transformationMatrix * vec4(vPosition,1.0);

outcolourValue =  vec3(vPosition.x+0.5,1.0,vPosition.y+0.5);


}