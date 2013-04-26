package ori.ogapi.util;

import java.util.Map;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.AbstractSet;
import java.util.Collection;

public class LinkedListMap<K,V> implements Map<K,V> {

	@Override
	public int size() {
		return _list.size();
	}

	@Override
	public void clear() {
		_list.clear();
	}

	@Override
	public boolean containsKey(Object k) {
		for (Entry<K,V> p : _list) {
			if (p.getKey().equals(k))
				return true;
		}
		return false;
	}

	@Override
	public boolean containsValue(Object v) {
		for (Entry<K,V> p : _list) {
			if (p.getValue() == v)
				return true;
		}
		return false;
	}


	@Override
	public Set<Entry<K,V>> entrySet() {
		return new InternalSet(_list);
	}

	@Override
	public boolean equals(Object o) {
		return this == o;
	}

	@Override
	public V get(Object k) {
		for (Entry<K,V> p : _list) {
			if (p.getKey().equals(k))
				return p.getValue();
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	// TODO
	@Override
	public Set<K> keySet() {
////	LinkedList<K> res = new LinkedList<K>();
////	for (Entry<K,V> e : _list)
////		res.add(e.getKey());
////	return res;
		return null;
	}

	@Override
	public V put(K k, V v) {
		V old = null;
		for (Entry<K,V> p : _list) {
			if (p.getKey() == k) {
				old = p.getValue();
				p.setValue(v);
				return old;
			}
		}
		_list.add(new PEntry<K,V>(k,v));
		return null;
	}

	@Override
	public void putAll(Map<? extends K, ? extends V> m) {
		for (Entry<? extends K,? extends V> p : m.entrySet())
			this.put(p.getKey(),p.getValue());
	}

	@Override
	public V remove(Object key) {
		for (Entry<K,V> p : _list) {
			if (p.getKey().equals(key)) {
				V old = p.getValue();
				_list.remove(p);
				return old;
			}
		}
		return null;
	}

	// TODO
	@Override
	public Collection<V> values() {
		LinkedList<V> res = new LinkedList<V>();
		for (Entry<K,V> e : _list)
			res.add(e.getValue());
		return res;
	}

	private LinkedList<Entry<K,V>> _list = new LinkedList<Entry<K,V>>();


	private class InternalSet extends AbstractSet<Entry<K,V>> {
		public InternalSet(LinkedList<Entry<K,V>> list) {
			_list = list;
		}
		@Override
		public Iterator<Entry<K,V>> iterator() {
			return _list.iterator();		
		}
		@Override
		public int size() {
			return _list.size();
		}
		private LinkedList<Entry<K,V>> _list;
	}

	private class PEntry<K,V> extends Pair2<K,V> implements Entry<K,V> {
		public PEntry(K k, V v) {
			super(k,v);
		}
		@Override
		public K getKey() {
			return first;
		}
		@Override
		public V getValue() {
			return second;
		}
		@Override
		public V setValue(V v) {
			V old = second;
			second = v;
			return old;
		}
	};


};

