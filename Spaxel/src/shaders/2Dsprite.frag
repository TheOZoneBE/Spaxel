#version 330 core

layout (location = 0) out vec4 color;

in vec2 pass_tex_coord;
in vec4 pass_tex_offset_scale;
in vec2 pass_alpha;

uniform sampler2D tex_sampler;

void main()
{
    mat2 scale = mat2(pass_tex_offset_scale.z,0,0,pass_tex_offset_scale.w);
    vec2 atlas_coord = scale * pass_tex_coord + pass_tex_offset_scale.xy;
    if (pass_alpha.y) {
        color = texture(tex_sampler, atlas_coord);
    }
    else {
        color = pass_alpha.y;
    }
	if (color == vec4(1.0,0.0,1.0,1.0)) {
	    discard;
	}
	else {
	    color.w = pass_alpha.x;
	}
}