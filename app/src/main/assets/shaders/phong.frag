#version 300 es

precision mediump float;

out vec4 colorOut;

in vec3 outcolourValue;

void main() {

colorOut = vec4(outcolourValue,1.0f);

}
