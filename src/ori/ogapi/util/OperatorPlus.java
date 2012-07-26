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
};

