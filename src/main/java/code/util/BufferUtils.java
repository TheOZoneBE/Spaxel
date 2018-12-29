package code.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;

public final class BufferUtils {
	private static final int BYTES_IN_FLOAT = 4;
	private static final int BYTES_IN_INT = 4;

	private BufferUtils() {
	}

	public static ByteBuffer createByteBuffer(byte[] array) {
		ByteBuffer result = ByteBuffer.allocateDirect(array.length).order(ByteOrder.nativeOrder());
		result.put(array).flip();
		return result;
	}

	public static FloatBuffer createFloatBuffer(double[] array) {
		int n = array.length;
		float[] ret = new float[n];
		for (int i = 0; i < n; i++) {
			ret[i] = (float) array[i];
		}
		return createFloatBuffer(ret);
	}

	public static FloatBuffer createFloatBuffer(float[] array) {
		FloatBuffer result = ByteBuffer.allocateDirect(array.length * BYTES_IN_FLOAT).order(ByteOrder.nativeOrder())
				.asFloatBuffer();
		result.put(array).flip();
		return result;
	}

	public static FloatBuffer allocateFloatBuffer(int size) {
		return ByteBuffer.allocateDirect(size * BYTES_IN_FLOAT).order(ByteOrder.nativeOrder()).asFloatBuffer();
	}

	public static IntBuffer createIntBuffer(int[] array) {
		IntBuffer result = ByteBuffer.allocateDirect(array.length * BYTES_IN_INT).order(ByteOrder.nativeOrder())
				.asIntBuffer();
		result.put(array).flip();
		return result;
	}

	public static FloatBuffer combineFloatBuffers(FloatBuffer one, FloatBuffer two) {
		FloatBuffer result = ByteBuffer.allocateDirect((one.capacity() + two.capacity()) * BYTES_IN_FLOAT)
				.order(ByteOrder.nativeOrder()).asFloatBuffer();
		result.put(one).put(two).flip();
		return result;
	}
}
