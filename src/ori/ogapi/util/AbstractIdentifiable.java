package ori.ogapi.util;

public class AbstractIdentifiable implements Identifiable {
	public AbstractIdentifiable() {
		_id = UNDEFINED;
	}
	public AbstractIdentifiable(int id) {
		_id = id;
	}
	public int getID() {
		return _id;
	}
	public void setID(int id) {
		_id = id;
	}
	private int _id;
};

