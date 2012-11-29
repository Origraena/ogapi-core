package ori.ogapi.util;

/**
 * Bounded double value.
 * Defines by a min, a max, and a current value.
 */
public class BoundedValue {

	public BoundedValue() {
		_min = 0.0;
		_max = 0.0;
		_value = 0.0;
	}

	public BoundedValue(double max) {
		_min = 0.0;
		_max = max;
		_value = _min;
	}

	public BoundedValue(double min, double max) {
		_min = min;
		_max = max;
		_value = _min;
	}

	public BoundedValue(double min, double max, double v) {
		_min = min;
		_max = max;
		_value = v;
	}


	public double min() {
		return _min;
	}

	public void setMin(double d) {
		_min = d;
		if (_value < _min)
			_value = _min;
	}

	public double max() {
		return _max;
	}

	public void setMax(double d) {
		_max = d;
		if (_value > _max)
			_value = _max;
	}

	public double value() {
		return _value;
	}

	public void setValue(double d) {
		_value = d;
		if (_value < _min)
			_value = _min;
		else if (_value > _max)
			_value = _max;
	}

	public double plus(double d) {
		this.setValue(_value + d);
		return _value;
	}

	public double minus(double d) {
		this.setValue(_value - d);
		return _value;
	}

	public double times(double d) {
		this.setValue(_value * d);
		return _value;
	}

	public double ratio() {
		double rng = _max - _min;
		return (_value-_min)/rng;
	}

	@Override
	public BoundedValue clone() {
		return new BoundedValue(_min,_max,_value);
	}

	private double _min;
	private double _max;
	private double _value;

};

