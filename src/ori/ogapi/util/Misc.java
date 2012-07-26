package ori.ogapi.util;

public class Misc {

	public static final int max(int array[]) {
		int value = Integer.MIN_VALUE;
		for (int i : array) {
			if (value < i)
				value = i;
		}
		return value;
	}

	public static final long max(long array[]) {
		long value = Long.MIN_VALUE;
		for (long i : array) {
			if (value < i)
				value = i;
		}
		return value;
	}

	public static final float max(float array[]) {
		float value = Float.MIN_VALUE;
		for (float i : array) {
			if (value < i)
				value = i;
		}
		return value;
	}

	public static final double max(double array[]) {
		double value = Double.MIN_VALUE;
		for (double i : array) {
			if (value < i)
				value = i;
		}
		return value;
	}

	public static final int min(int array[]) {
		int value = Integer.MAX_VALUE;
		for (int i : array) {
			if (value > i)
				value = i;
		}
		return value;
	}

	public static final long min(long array[]) {
		long value = Long.MAX_VALUE;
		for (long i : array) {
			if (value > i)
				value = i;
		}
		return value;
	}

	public static final float min(float array[]) {
		float value = Float.MAX_VALUE;
		for (float i : array) {
			if (value > i)
				value = i;
		}
		return value;
	}

	public static final double min(double array[]) {
		double value = Double.MAX_VALUE;
		for (double i : array) {
			if (value > i)
				value = i;
		}
		return value;
	}


	/** This class cannot be instantiated. */
	private Misc() { }
};

