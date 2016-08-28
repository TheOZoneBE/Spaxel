#version 330 core

layout (location = 0) out vec4 color;

in vec2 pass_tex_coord;
in vec3 pass_tex_offset_alpha;

uniform int number_of_rows;
uniform sampler2D tex_sampler;

void main()
{
    vec2 atlas_coord = (pass_tex_coord / number_of_rows) + pass_tex_offset_alpha.xy;

	color = texture(tex_sampler, atlas_coord);
	if (color == vec4(1.0,0.0,1.0,1.0)) {
	    discard;
	}
}