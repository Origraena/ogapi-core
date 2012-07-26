package ori.ogapi.lists;

import ori.ogapi.util.Iterator;
import ori.ogapi.util.AdaptedIterator;

/**
 * Generic linked list.
 * <p>Head and queue elements are not defined in this class but in its subclasses.</p>
 */
public abstract class LinkedList<E> extends java.util.LinkedList<E> {

	/**
	 * Return the head.
	 */
	public abstract E get();

	/**
	 * Return and remove the head.
	 */
	public abstract E pop();
	
	/**
	 * Insert at the queue of the list.
	 */
	public void put(E e) {
		addLast(e);
	}

	public Iterator iterator() {
		return new AdaptedIterator(super.iterator());
	}

}


