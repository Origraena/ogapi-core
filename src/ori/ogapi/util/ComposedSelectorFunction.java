package ori.ogapi.util;

import java.util.Comparator;

public class ComposedSelectorFunction<T> implements SelectorFunction<T> {

	public ComposedSelectorFunction() { }

	public ComposedSelectorFunction(Filter<T> f) {
		_filter = f;
	}

	public ComposedSelectorFunction(Comparator<T> c) {
		_comparator = c;
	}

	public ComposedSelectorFunction(Filter<T> f, Comparator<T> c) {
		_filter = f;
		_comparator = c;
	}

	public void setFilter(Filter<T> f) {
		_filter = f;
	}

	public void setComparator(Comparator<T> c) {
		_comparator = c;
	}

	@Override
	public boolean isValid(T t) {
		if (t == null)
			return false;
		return _filter.filter(t);
	}

	@Override
	public boolean isBetter(T t1, T t2) {
		if (t1 == null)
			return false;
		if (t2 == null)
			return true;
		return (_comparator.compare(t1,t2) < 0);
	}

	private Filter<T> _filter;
	private Comparator<T> _comparator;

};

