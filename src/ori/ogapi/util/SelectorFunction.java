package ori.ogapi.util;

public interface SelectorFunction<T> {

	public boolean isValid(T t);
	public boolean isBetter(T t1, T t2);

};

