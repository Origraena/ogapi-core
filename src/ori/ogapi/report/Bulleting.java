package ori.ogapi.report;

public interface Bulleting {

	public String bullet(int n, int tabLevel);

	public static final Bulleting NULL = new Bulleting() {
		@Override public String bullet(int n, int tabLevel) {
			return "";
		}
	};

	public static final Bulleting NUMBERING = new Bulleting() {
		@Override public String bullet(int n, int tabLevel) {
			return String.valueOf(n)+". ";
		}
	};

};

