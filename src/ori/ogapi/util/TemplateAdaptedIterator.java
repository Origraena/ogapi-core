package ori.ogapi.util;

import java.util.NoSuchElementException;
import java.lang.UnsupportedOperationException;

public class TemplateAdaptedIterator<T1, T2 extends T1> implements Iterator<T1> {
	
	public TemplateAdaptedIterator(java.util.Iterator<T2> adapted) {
		_target = adapted;
	}
	public T1 next() throws NoSuchElementException {
		try {
			return (T1)(_target.next());
		}
		catch (ClassCastException e) {
			// never happns
			System.err.println("class: TemplateAdaptedIterator => should never happen");
			System.err.println(e);
			return null;
		}
	}
	public boolean hasNext() {
		return _target.hasNext();
	}
	public Iterator<T1> iterator() {
		return this;
	}
	public void remove() throws UnsupportedOperationException {
		_target.remove();
	}

	private java.util.Iterator<T2> _target;

};

