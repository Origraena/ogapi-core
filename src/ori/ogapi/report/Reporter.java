package ori.ogapi.report;

import ori.ogapi.lists.Lifo;

public abstract class Reporter {

	public Reporter() {
		_sectionCpt.put(new Integer(0));
	}
	
	public void report(Object o) {
		if (o instanceof Reportable)
			((Reportable)o).reportIn(this);
		else
			report(o.toString());
	}
	public void report(String string) {
		String lines[] = string.split(_endLine);
		String first[];
		String second[];
		boolean t = true;
		boolean l = true;
		for (String line : lines) {
			if (l)
				l = false;
			else
				newLine();
			t = true;
			first = line.split(_startToken);
			for (String f : first) {
				if (t) {
					doReport(f);
					t = false;
				}
				else {
					second = f.split(_endToken);
					if (second.length != 2)
						return;
					processToken(second[0]);
					doReport(second[1]);
				}
			}
		}
	}
	public void report(char array[]) {
		report(new String(array));
	}
	public void report(boolean b) {
		doReport(String.valueOf(b));
	}
	public void report(char c) {
		doReport(String.valueOf(c));
	}
	public void report(int i) {
		doReport(String.valueOf(i));
	}
	public void report(float f) {
		doReport(String.valueOf(f));
	}
	public void report(double d) {
		doReport(String.valueOf(d));
	}
	public void report(long l) {
		doReport(String.valueOf(l));
	}

	public void reportln(Object o) {
		report(o);
		newLine();
	}
	public void reportln(String string) {
		report(string);
		newLine();
	}
	public void reportln(char array[]) {
		report(array);
		newLine();
	}
	public void reportln(boolean b) {
		report(b);
		newLine();
	}
	public void reportln(char c) {
		report(c);
		newLine();
	}
	public void reportln(int i) {
		report(i);
		newLine();
	}
	public void reportln(float f) {
		report(f);
		newLine();
	}
	public void reportln(double d) {
		report(d);
		newLine();
	}
	public void reportln(long l) {
		report(l);
		newLine();
	}

	public void reportnl(Object o) {
		newLine();
		report(o);
	}
	public void reportnl(String string) {
		newLine();
		report(string);
	}
	public void reportnl(char array[]) {
		newLine();
		report(array);
	}
	public void reportnl(boolean b) {
		newLine();
		report(b);
	}
	public void reportnl(char c) {
		newLine();
		report(c);
	}
	public void reportnl(int i) {
		newLine();
		report(i);
	}
	public void reportnl(float f) {
		newLine();
		report(f);
	}
	public void reportnl(double d) {
		newLine();
		report(d);
	}
	public void reportnl(long l) {
		newLine();
		report(l);
	}

	public void reportline(Object o) {
		newLine();
		report(o);
		newLine();
	}
	public void reportline(String string) {
		newLine();
		report(string);
		newLine();
	}
	public void reportline(char array[]) {
		newLine();
		report(array);
		newLine();
	}
	public void reportline(boolean b) {
		newLine();
		report(b);
		newLine();
	}
	public void reportline(char c) {
		newLine();
		report(c);
		newLine();
	}
	public void reportline(int i) {
		newLine();
		report(i);
		newLine();
	}
	public void reportline(float f) {
		newLine();
		report(f);
		newLine();
	}
	public void reportline(double d) {
		newLine();
		report(d);
		newLine();
	}
	public void reportline(long l) {
		newLine();
		report(l);
		newLine();
	}


	public void setTabSize(int value) {
		_tabSize = value;
		if (_tabSize < 0)
			_tabSize = 0;
	}

	public void setBulleting(Bulleting value) {
		if (value != null)
			_bulleting = value;
	}

	// TODO
	// usefull ?
	// compare to new Reporter() ?
	public void newReport() {
		_sectionCpt = new Lifo<Integer>();
		_sectionCpt.put(new Integer(0));
		_tabLevel = 0;
		_first = true;
		doReport(_endLine);
	}
	public void newLine() {
		doReport(_endLine);
		doFlush();
		if (_inSection)
			_tabLevel++;
		String tab = "";
		for (int i = 0 ; i < _tabSize ; i++)
			tab += " ";
		for (int i = 0 ; i < _tabLevel ; i++)
			doReport(tab);
		if (_inSection)
			_tabLevel--;
	}
	public void newSection() {
		newSection("");
	}
	public void newSection(String title) {
		int old = _sectionCpt.pop().intValue();
		_sectionCpt.put(new Integer(old+1));
		_inSection = false;
		if (_first)
			_first = false;
		else
			newLine();
		doReport(_bulleting.bullet(old+1,_tabLevel));
		doReport(title);
		_inSection = true;
		newLine();
	}

	public void incSection() {
		incSection(null);
	}
	public void incSection(String title) {
		_tabLevel++;
		_sectionCpt.put(new Integer(0));
		if (title != null)
			newSection(title);
		//else
		//	newLine();
	}

	public void decSection() {
		_tabLevel--;
		if (_tabLevel < 0) {
			//System.out.println("tab level was "+_tabLevel);
			_tabLevel = 0;
			//System.out.println("(set to 0)");
			// TODO log
		}
		else
			_sectionCpt.pop();
	}

	protected void processToken(String t) {
		if (t.equals(_newSection))
			newSection();
		else if (t.equals(_incSection))
			incSection();
		else if (t.equals(_decSection))
			decSection();
		else
			System.out.println("Unrecognized token : "+t);
			// TODO log
	}

	protected abstract void doReport(String s);
	protected abstract void doFlush();

	// output parameters
	private int _tabSize = 2;
	private Bulleting _bulleting = Bulleting.NULL;

	// tokens
	private String _startToken = "__@";
	private String _endToken = ";";
	private String _newSection = "SEC";
	private String _incSection = "INC";
	private String _decSection = "DEC";
	private String _endLine = "\n";

	// internal
	private boolean _first = true;
	private boolean _inSection = false;
	private int _tabLevel = 0;
	private Lifo<Integer> _sectionCpt = new Lifo<Integer>();
};

