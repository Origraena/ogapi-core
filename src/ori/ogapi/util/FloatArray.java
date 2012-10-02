package ori.ogapi.util;

import java.util.NoSuchElementException;

public class FloatArray {

	public FloatArray() {
		_size = 0;
		_capacity = 0;
	}

	public FloatArray(int capacity, int foot) {
		_size = 0;
		_capacity = capacity;
		_foot = foot;
		if (_capacity <= 0)
			_array = null;
		else
			_array = new float[_capacity];
	}

	public FloatArray(Iterable<Float> array) {
		_size = 0;
		_capacity = 0;
		for (Float i : array)
			this.add(i.floatValue());
	}

	public float get(int index) throws NoSuchElementException {
		if (index >= _size)
			throw new NoSuchElementException();
		return _array[index];
	}

	public void add(float e) {
		if (_capacity - _size <= 0)
			this.recap();
		_array[_size] = e;
		_size++;
	}

	public void set(int index, float value) throws NoSuchElementException {
		if (index >= _size)
			throw new NoSuchElementException();
		_array[index] = value;
	}

	public void resize(int newSize) {
		if (newSize < 0)
			newSize = 0;
		if (newSize > _capacity)
			recap();
		_size = newSize;
	}

	protected void recap() {
		this.recap(_capacity + _foot);
	}

	protected void recap(int capacity) {
		float copy[] = _array;
		_array = new float[capacity];
		for (int i = 0 ; i < capacity ; i++) {
			if ((i < _size) && (copy != null))
				_array[i] = copy[i];
			else
				_array[i] = 0;
		}
		_capacity = capacity;
		if (_size > _capacity)
			_size = _capacity;
	}


	private int   _capacity;
	private int   _size;
	private float _array[];

	private int   _foot = 8;

};

