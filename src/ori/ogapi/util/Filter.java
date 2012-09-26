package ori.ogapi.util;

public interface Filter<T> {
	public boolean filter(T t);
//	public static final Filter<T> ALL = new Filter<T>() {
//		@Override public boolean filter(T t) { return true; }
//	};
};

