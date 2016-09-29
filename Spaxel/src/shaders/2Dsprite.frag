#version 330 core

layout (location = 0) out vec4 color;

in vec2 pass_tex_coord;
in vec4 pass_tex_offset_alpha;

uniform sampler2D tex_sampler;

void main()
{
    vec2 atlas_coord = (pass_tex_offset_alpha.z * pass_tex_coord) + pass_tex_offset_alpha.xy;

	color = texture(tex_sampler, atlas_coord);
	if (color == vec4(1.0,0.0,1.0,1.0)) {
	    discard;
	}
	else {
	    color.w = pass_tex_offset_alpha.w;
	}
}