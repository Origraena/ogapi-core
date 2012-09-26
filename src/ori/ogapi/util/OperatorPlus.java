package ori.ogapi.util;

/**
 * Interface which represents the operator +.
 * <p>
 * The exec method must take B and C as parameters, and return their sums as A type.
 * </p>
 * @param A The return type.
 * @param B The first parameter type.
 * @param C The second parameter type.
 */
public interface OperatorPlus<A,B,C> {
	A exec(B b, C c);

	public static final OperatorPlus<Integer,Integer,Integer> INTEGER =
		new OperatorPlus<Integer,Integer,Integer>() {
			@Override
			public Integer exec(Integer b, Integer c) {
				return new Integer(b.intValue()+c.intValue());
			}
		};

	public static final OperatorPlus<Long,Long,Long> LONG =
		new OperatorPlus<Long,Long,Long>() {
			@Override
			public Long exec(Long b, Long c) {
				return new Long(b.longValue()+c.longValue());
			}
		};
		
	public static final OperatorPlus<Float,Float,Float> FLOAT =
		new OperatorPlus<Float,Float,Float>() {
			@Override
			public Float exec(Float b, Float c) {
				return new Float(b.floatValue()+c.floatValue());
			}
		};

	public static final OperatorPlus<Double,Double,Double> DOUBLE =
		new OperatorPlus<Double,Double,Double>() {
			@Override
			public Double exec(Double b, Double c) {
				return new Double(b.doubleValue()+c.doubleValue());
			}
		};

};

