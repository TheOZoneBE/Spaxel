#version 330 core

layout (location = 0) in vec4 position;
layout (location = 1) in vec2 tex_coord;
layout (location = 2) in vec4 tfm_comp;
layout (location = 3) in vec4 tex_offset_alpha;


uniform mat4 projection_matrix;
uniform mat4 view_matrix = mat4(1.0);
uniform mat3 transformation_matrix = mat3(1.0);

out vec2 pass_tex_coord;
out vec4 pass_tex_offset_alpha;

void main()
{
    float sinus = sin(tfm_comp.z);
    float cosin = cos(tfm_comp.z);
    mat3 transformation = mat3(cosin, -sinus, 0,sinus,cosin, 0, tfm_comp.x, tfm_comp.y, 1);
    vec3 temp_pos = tfm_comp.w * transformation * vec3(position.xyw);

	gl_Position = projection_matrix * view_matrix * vec4(temp_pos.xy, 0, 1);
	pass_tex_coord = tex_coord;
	pass_tex_offset_alpha = tex_offset_alpha;
}