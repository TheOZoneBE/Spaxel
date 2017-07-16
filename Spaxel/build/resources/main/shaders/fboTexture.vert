#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tex_coord;

out vec2 pass_tex_coord;

uniform mat4 projection_matrix;
uniform mat3 transformation_matrix = mat3(1.0);

void main()
{
    vec3 temp_pos = transformation_matrix * vec3(position.xyw);

	gl_Position = projection_matrix * vec4(temp_pos.xy, 0, 1.0);
	gl_Position += vec4(-1,-1,0,0);

	pass_tex_coord = tex_coord;
}