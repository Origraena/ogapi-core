package ori.ogapi.report;

import java.io.PrintWriter;
import java.io.OutputStream;

public class PrintReporter extends Reporter {

	public PrintReporter(PrintWriter p) {
		_printer = p;
	}
	
	public PrintReporter(OutputStream out) {
		_printer = new PrintWriter(out);
		_out = out;
	}

	@Override
	protected int doReportImpl(String s) {
		_printer.print(s);
		return s.length();
	}

	@Override
	protected void doFlush() {
		_printer.flush();
	}

	@Override
	public void close() {
		if ((_out == System.out) || (_out == System.err))
			return;
		_printer.close();
	}

	private OutputStream _out = null;
	private PrintWriter _printer;

};

