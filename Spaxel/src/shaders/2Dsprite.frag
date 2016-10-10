#version 330 core

layout (location = 0) out vec4 color;

in vec2 pass_tex_coord;
in vec4 pass_tex_offset_scale;
in vec2 pass_alpha;

uniform sampler2D tex_sampler;

void main()
{
    color = vec4(1.0,1.0,1.0,1.0);

    mat2 scale = mat2(pass_tex_offset_scale.z,0,0,pass_tex_offset_scale.w);
    vec2 atlas_coord = scale * pass_tex_coord + pass_tex_offset_scale.xy;
    if (pass_alpha.y == 0) {
        color = texture(tex_sampler, atlas_coord);
    }
    else {
        int c = int(pass_alpha.y);
        int r = (c & 0xff0000) >> 16;
        int g = (c & 0x00ff00) >> 8;
        int b = (c & 0x0000ff);
        int a = (c & 0xff000000) >> 24;
        color = vec4(r/256,g/256,b/256, a/256);
    }
	if (color == vec4(1.0,0.0,1.0,1.0)) {
	    discard;
	}
	else {
	    color = vec4(1.0,1.0,1.0,1.0);
	    color.w = pass_alpha.x;
	}
}