package ori.ogapi.util;

/**
 * Implementation of OperatorPlus for <code>long</code> type.
 */
public class IntegerOperatorPlus implements OperatorPlus1T<Integer> {


	public Integer exec(Integer b, Integer c) {
		return new Integer(b+c);
	}

};

