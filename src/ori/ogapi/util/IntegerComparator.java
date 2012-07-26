package ori.ogapi.util;

import java.util.Comparator;

public class IntegerComparator implements Comparator<Integer> {

	public int compare(Integer i, Integer j) {
		return i.intValue() - j.intValue();
	}

};

