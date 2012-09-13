package ori.ogapi.report;

import ori.ogapi.lists.Lifo;

public abstract class Reporter {

	public Reporter() {
		_sectionCpt.put(new Integer(0));
	}

	public void report(Object o) {
		if (o == null)
			return;
		if (o instanceof Reportable)
			((Reportable)o).reportIn(this);
		else
			report(o.toString());
	}
	public void report(String string) {
		int cpt = 0;
		int nbL = 0;
		int length = string.length();
		while ((cpt < length) && (string.charAt(cpt) == '\n'))
			cpt++;
		if (cpt > 0) {
			for (int i = 0 ; i < cpt ; i++)
				newLine();
			string = string.substring(cpt,length);
			length -= cpt;
		}
		cpt = length-1;
		while ((cpt >= 0) && (string.charAt(cpt) == '\n'))
			cpt--;
		if (cpt < length-1) {
			string = string.substring(0,cpt+1);
		}

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
					processToken(second[0],second[1]);
				}
			}
		}

		if (cpt < length-1) {
			for (int i = length-1 ; i > cpt ; i--)
				newLine();
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
		_lineCpt = 0;
		doReport(_endLine);
	}
	protected void reportNewLine() {
		_newLine = false;
		String tab = "";
		String margin = "";
		if (_inSection)
			for (int i = 0 ; i < _parMargin ; i++)
				margin += " ";
		for (int i = 0 ; i < _tabSize ; i++)
			tab += " ";
		for (int i = 0 ; i < _tabLevel ; i++)
			doReport(tab);
		doReport(margin);
	}
	public void newLine() {
		doReport(_endLine);
		_lineCpt = 0;
		_newLine = true;
		doFlush();
	}
	public void newSection() {
		newSection("");
	}
	public void newSection(String title) {
		int old = _sectionCpt.pop().intValue();
		_sectionCpt.put(new Integer(old+1));
		_inSection = false;
		if (!_newLine) 
			newLine();
		doReport(_bulleting.bullet(old+1,_tabLevel));
		doReport(title);
		_inSection = true;
		if (title.length() > 0)
			newLine();
	}
	public void newTitle(String title) {
		final int length = title.length();
		if (length == 0) {
			title = "NO_TITLE";
			newTitle(title);
			return;
		}
		if (_first)
			_first = false;
		else
			newLine();
		StringBuilder line = new StringBuilder();
		int i;
		for (i = 0 ; i < _titleMargin ; i++)
			line.append(' ');
		line.append(_cross);
		for (i = 0 ; i < length + 2 ; i++)
			line.append(_horizontal);
		line.append(_cross);
		doReport(line.toString());
		newLine();
		StringBuilder line2 = new StringBuilder();
		for (i = 0 ; i < _titleMargin ; i++)
			line2.append(' ');
		line2.append(_vertical);
		line2.append(' ');
		line2.append(title);
		line2.append(' ');
		line2.append(_vertical);
		doReport(line2.toString());
		newLine();
		doReport(line.toString());
		_isTitle = false;
	}

	public void incSection() {
		incSection(null);
	}
	public void incSection(String title) {
		if ((title != null) && (title.length() > 0))
			newSection(title);
		_tabLevel++;
		_sectionCpt.put(new Integer(0));
		//else
		//	newLine();
	}

	public void decSection() {
		_tabLevel--;
		if (_tabLevel < 0) {
			_tabLevel = 0;
			// TODO log
		}
		else
			_sectionCpt.pop();
	}

	protected void processToken(String t, String arg) {
		if (t.equals(_newSection))
			newSection();
		else if (t.equals(_incSection))
			incSection();
		else if (t.equals(_decSection))
			decSection();
		else if (t.equals(_titleTok))
			_isTitle = true;
		else
			System.out.println("Unrecognized token : "+t);
			// TODO log
		if (_isTitle) {
			newTitle(arg);
			_isTitle = false;
		}
		else
			doReport(arg);
	}

	protected void doReport(String s) {
		if (_newLine)
			reportNewLine();
		_lineCpt += doReportImpl(s);
	}
	abstract public void close();

	/**
	 * Must returns the number of char written.
	 */
	protected abstract int doReportImpl(String s);
	protected abstract void doFlush();

	// output parameters
	private int _tabSize = 4;
	private int _titleMargin = 8;
	private int _parMargin = 2;
	private Bulleting _bulleting = Bulleting.NULL;
	private char _cross = '+';
	private char _vertical = '|';
	private char _horizontal = '-';


	// tokens
	private String _startToken = "__@";
	private String _endToken = ";";
	private String _newSection = "SEC";
	private String _incSection = "INC";
	private String _decSection = "DEC";
	private String _endLine = "\n";
	private String _titleTok = "TITLE";

	// internal
	private boolean _isTitle = false;
	private boolean _first = true;
	private boolean _newLine = true;
	private boolean _inSection = true;
	private int _tabLevel = 0;
	private int _lineCpt = 0;
	private Lifo<Integer> _sectionCpt = new Lifo<Integer>();
};

