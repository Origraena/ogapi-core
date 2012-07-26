package ori.ogapi.lists;

/**
 * Last In First Out implementation of linked list.
 */
public class Lifo<E> extends LinkedList<E> {
	
	/** @see java.io.Serializable */
	private static final long serialVersionUID = 2011022601L;

	/**
	 * Return the last element which has been added.
	 */
	public E get() {
		return super.getLast();
	}

	/**
	 * Return and remove the last element which has been added.
	 */
	public E pop() {
		return super.pollLast();
	}

};

