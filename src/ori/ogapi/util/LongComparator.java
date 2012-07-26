package ori.ogapi.util;

import java.util.Comparator;

public class LongComparator implements Comparator<Long> {

	public int compare(Long i, Long j) {
		return (int)(i.longValue() - j.longValue());
	}

};

