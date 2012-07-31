package ori.ogapi.util;

import java.util.Iterator;
import java.util.Map;
import java.util.Collection;
import java.util.Set;
import java.util.ArrayList;
import java.util.concurrent.ConcurrentSkipListSet;

public class IdentifiableIndexedMap<K extends Identifiable,T> implements Map<K,T> {

	public class Entry implements Map.Entry<K,T> {
		public Entry(K k, T v) {
			_key = k;
			_value = v;
		}
		@Override public K getKey() {
			return _key;
		}
		@Override public T setValue(T value) {
			T old = _value;
			_value = value;
			return old;
		}
		@Override public T getValue() {
			return _value;
		}
		private K _key;
		private T _value;
	};

	public IdentifiableIndexedMap() { }

	public IdentifiableIndexedMap(int coefFreeCell) {
		if ((coefFreeCell >= 0) && (coefFreeCell <= 100))
		_coefFreeCell = coefFreeCell;
	}

	@Override
	public void clear() {
		_data.clear();
	}

	@Override
	public boolean containsKey(Object key) {
		if (!(key instanceof Identifiable))
			return false;
		int id = ((Identifiable)key).getID();
		if ((id >= size()) || (id < 0))
			return false;
		return (_data.get(id).getKey() == key);
	}

	/** Strict equality. */
	@Override
	public boolean containsValue(Object value) {
		for (T t : values())
			if (t == value)
				return true;
		return false;
	}

	@Override
	public T get(Object key) {
		if (!(key instanceof Identifiable))
			return null;
		if (((Identifiable)key).getID() >= _nextID)
			return null;
	//	Entry res = _data.get(((Identifiable)key).getID());
	//	if (res == null) {
	//		System.err.println("reading a null case ?");
	//		return null;
	//	}
	//	return res.getValue();
		return _data.get(((Identifiable)key).getID()).getValue();
	}

	@Override
	public boolean isEmpty() {
		return (size() == 0);
	}
	
	/** Always add an element.  */
	@Override
	public T put(K key, T value) {
		key.setID(_nextID++);
		_data.add(new Entry(key,value));
		return null;
	}

	@Override
	public void putAll(Map<? extends K,? extends T> map) {
		try {
			for (Map.Entry entry : map.entrySet())
				put((K)(entry.getKey()),(T)(entry.getValue()));
		}
		catch (Exception e) {
			// TODO log
		}
	}

	@Override
	public T remove(Object key) {
		if (!(key instanceof Identifiable))
			return null;
		T t = get(key);
		if (t != null) {
			_data.set(((Identifiable)key).getID(),null);
			incRemove();
		}
		return t;
	}

	private void incRemove() {
		_nbRemoves++;
		if (((_nbRemoves*100)/_nextID) >= _coefFreeCell) {
			Iterator<Entry> iterator = _data.iterator();
			Entry e;
			_nextID = 0;
			_nbRemoves = 0;
			while (iterator.hasNext()) {
				e = iterator.next();
				if (e == null)
					iterator.remove();
				else
					e.getKey().setID(_nextID++);
			}
		}
	}

	@Override
	public int size() {
		return _nextID - _nbRemoves;
	}

	@Override
	public Set<Map.Entry<K,T> > entrySet() {
		return new ConcurrentSkipListSet<Map.Entry<K,T> >(_data);
	}

	@Override
	public Set<K> keySet() {
		ConcurrentSkipListSet<K> set = new ConcurrentSkipListSet<K>();
		for (Entry entry : _data) {
			if (entry.getKey() != null)
				set.add(entry.getKey());
		}
		return set;
	}

	@Override
	public Collection<T> values() {
		ArrayList<T> set = new ArrayList<T>(size());
		for (Entry entry : _data) {
			if (entry.getKey() != null)
				set.add(entry.getValue());
		}
		return set;
	}

	private int _nextID = 0;
	private int _nbRemoves = 0;
	private int _coefFreeCell = 10;
	private ArrayList<Entry> _data = new ArrayList<Entry>();

};

