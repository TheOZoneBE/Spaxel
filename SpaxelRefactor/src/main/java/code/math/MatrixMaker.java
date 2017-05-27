package code.math;

public class MatrixMaker {

	public static MatrixF getTransformationMatrix(float x, float y, float rot, float scale) {
		float sin = (float)Math.sin(rot);
		float cos = (float)Math.cos(rot);
		MatrixF mat = new MatrixF(3, 3, new float[] { (float)cos, (float)sin, x, (float)-sin, (float)cos, y, 0, 0, 1 });
		return mat.multiply(scale);
	}

	public static MatrixF orthographic(float left, float right, float bottom, float top, float near, float far){
		MatrixF mat = new MatrixF(4,4, new float[] {
			2/(right-left),0,0,-(right+left)/(right-left),
				0,2/(top-bottom), 0,-(top+bottom)/(top-bottom),
				0,0,-2/(far-near), -(far+near)/(far-near),
				0,0,0,1
		});
		return mat;
	}

}
