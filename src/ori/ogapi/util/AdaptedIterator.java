package ori.ogapi.util;

import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class AdaptedIterator<E> implements Iterator<E> {
	
	public AdaptedIterator(java.util.Iterator<E> adapted) {
		_target = adapted;
	}
	public E next() throws NoSuchElementException {
		return _target.next();
	}
	public boolean hasNext() {
		return _target.hasNext();
	}
	public Iterator<E> iterator() {
		return this;
	}
	public void remove() throws UnsupportedOperationException {
		_target.remove();
	}

	private java.util.Iterator<E> _target;

};

