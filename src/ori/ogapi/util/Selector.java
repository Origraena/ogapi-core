package ori.ogapi.util;

import java.util.Comparator;

public class Selector<T> {

	public Selector() { }

	public Selector(SelectorFunction<T> function) {
		_function = function;
	}

	public Selector(Filter<T> f, Comparator<T> c) {
		_function = new ComposedSelectorFunction<T>(f,c);
	}

	public SelectorFunction<T> function() {
		return _function;
	}

	public void setFunction(SelectorFunction<T> o) {
		_function = o;
	}

	public T selectFrom(Iterable<T> list) {
		T result = null;
		for (T t : list) {
			if ((_function.isValid(t)) && (_function.isBetter(t,result)))
				result = t;
		}
		return result;
	}

	private SelectorFunction<T> _function;

};

