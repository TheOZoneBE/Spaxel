#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tex_coord;

uniform mat4 projection_matrix;
uniform mat4 view_matrix = mat4(1.0);
uniform mat4 transformation_matrix = mat4(1.0);

out vec2 pass_tex_coord;

void main()
{
	gl_Position = projection_matrix * view_matrix * transformation_matrix * position;
	pass_tex_coord = tex_coord;
}