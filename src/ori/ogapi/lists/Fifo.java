package ori.ogapi.lists;

/**
 * First In First Out implementation of linked list.
 */
public class Fifo<E> extends LinkedList<E> {

	/** @see java.io.Serializable */
	private static final long serialVersionUID = 2011022602L;

	/**
	 * Return the first element which has been added.
	 */
	public E get() {
		return super.getFirst();
	}

	/**
	 * Return and remove the first element which has been added.
	 */
	public E pop() {
		return super.pollFirst();
	}

};

