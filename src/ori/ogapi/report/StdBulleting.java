package ori.ogapi.report;

import java.util.ArrayList;

public class StdBulleting implements Bulleting {

	public StdBulleting() {
		_bullets = new ArrayList<String>();
	}

	public StdBulleting(Iterable<String> bullets) {
		_bullets = new ArrayList<String>();
		for (String b : bullets)
			_bullets.add(b);
	}

	public StdBulleting(ArrayList<String> bullets) {
		_bullets = bullets;
	}

	public void addBullet(String b) {
		_bullets.add(b);
	}

	public void addBulletAt(String b, int level) {
		_bullets.add(level,b);
	}

	public String bullet(int n, int tabLevel) {
		if (tabLevel < 0)
			return "";
		if (tabLevel >= _bullets.size())
			tabLevel = _bullets.size()-1;
		return _bullets.get(tabLevel);
	}

	private ArrayList<String> _bullets;

};

