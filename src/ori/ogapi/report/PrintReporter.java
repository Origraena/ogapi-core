package ori.ogapi.report;

import java.io.PrintWriter;

public class PrintReporter extends Reporter {

	public PrintReporter(PrintWriter p) {
		_printer = p;
	}
	
	@Override
	protected void doReport(String s) {
		_printer.print(s);
	}

	@Override
	protected void doFlush() {
		_printer.flush();
	}

	private PrintWriter _printer;

};

