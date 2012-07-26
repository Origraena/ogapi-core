package ori.ogapi.util;

import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public interface Iterator<E> extends Iterable<E>,java.util.Iterator<E> {

	public E next() throws NoSuchElementException;
	public boolean hasNext();
	public Iterator<E> iterator();
	public void remove() throws UnsupportedOperationException;

};

