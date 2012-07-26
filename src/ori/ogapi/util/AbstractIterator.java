package ori.ogapi.util;

import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public abstract class AbstractIterator<E> implements Iterator<E> {

	public Iterator<E> iterator() {
		return this;
	}

	public void remove() throws UnsupportedOperationException {
		throw new UnsupportedOperationException();
	}

};

