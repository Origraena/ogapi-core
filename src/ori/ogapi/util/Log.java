package ori.ogapi.util;

import java.util.logging.Logger;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.StreamHandler;

/**
 * Convenient class for logs.
 */
public class Log {

	// level constants in descending order
	public static final Level   DEBUG        = DebugLevel.instance();
	public static final Level   INFO         = Level.INFO;
	public static final Level   WARNING      = Level.WARNING;
	public static final Level   ERROR        = ErrorLevel.instance();
	public static final Level   RESULT       = ResultLevel.instance();

	public static final Level   LOGGER_LEVEL = Level.ALL;
	public static final String  FILENAME     = "./logs/ori";
		

	public static void d(String msg) {
		l(DEBUG,msg);
	}
	public static void d(Object source, String msg) {
		l(DEBUG,source.getClass().getName(),"",msg);
	}
	public static void d(Object source, String sourceMethod, String msg) {
		l(DEBUG,source.getClass().getName(),sourceMethod,msg);
	}
	public static void d(String sourceClass, String sourceMethod, String msg) {
		l(DEBUG,sourceClass,sourceMethod,msg);
	}
	public static void i(String msg) {
		l(INFO,msg);
	}
	public static void i(Object source, String msg) {
		l(INFO,source.getClass().getName(),"",msg);
	}
	public static void i(Object source, String sourceMethod, String msg) {
		l(INFO,source.getClass().getName(),sourceMethod,msg);
	}
	public static void i(String sourceClass, String sourceMethod, String msg) {
		l(INFO,sourceClass,sourceMethod,msg);
	}
	public static void w(String msg) {
		l(WARNING,msg);
	}
	public static void w(Object source, String msg) {
		l(WARNING,source.getClass().getName(),"",msg);
	}
	public static void w(Object source, String sourceMethod, String msg) {
		l(WARNING,source.getClass().getName(),sourceMethod,msg);
	}
	public static void w(String sourceClass, String sourceMethod, String msg) {
		l(WARNING,sourceClass,sourceMethod,msg);
	}
	public static void e(String msg) {
		l(ERROR,msg);
	}
	public static void e(Object source, String msg) {
		l(ERROR,source.getClass().getName(),"",msg);
	}
	public static void e(Object source, String sourceMethod, String msg) {
		l(ERROR,source.getClass().getName(),sourceMethod,msg);
	}

	public static void e(String sourceClass, String sourceMethod, String msg) {
		l(ERROR,sourceClass,sourceMethod,msg);
	}
	public static void r(String msg) {
		l(RESULT,msg);
	}

	public static void l(Level lvl, String sourceClass, String sourceMethod, String msg) {
		INSTANCE.logger.logp(lvl,sourceClass,sourceMethod,msg);
	}
	public static void l(Level lvl, Object o, String sourceMethod, String msg) {
		INSTANCE.logger.logp(lvl,o.getClass().getName(),sourceMethod,msg);
	}
	public static void l(Level lvl, Object o, String msg) {
		INSTANCE.logger.logp(lvl,o.getClass().getName(),"",msg);
	}
	public static void l(Level lvl, String msg) {
		INSTANCE.logger.log(lvl,msg);
	}

	public static void disable() {
		INSTANCE.logger.setLevel(Level.OFF);
	}
	public static void enable() {
		INSTANCE.logger.setLevel(LOGGER_LEVEL);
	}

	private Log() {
		this.logger = Logger.getLogger("ORI");
		this.logger.setLevel(LOGGER_LEVEL);
		this.logger.setUseParentHandlers(false);
		try {
			this.stderr = new ConsoleHandler();
			this.stderr.setFormatter(new ErrFormatter(false));
			this.stderr.setLevel(this.stderrlevel);
			this.stderr.setFilter(NoResultFilter.instance());
			this.logger.addHandler(this.stderr);
		}
		catch (Exception e) {
			System.err.println("LOG: error while loading error console handler");
			System.err.println(e);
			e.printStackTrace();
		}
		try {
			this.fileerr = new FileHandler(FILENAME+"-"+
			            		          (System.currentTimeMillis() / 1000)+
			                   			  this.exterr);
			this.fileerr.setFormatter(new ErrFormatter(true));
			this.fileerr.setLevel(this.fileerrlevel);
			this.fileerr.setFilter(NoResultFilter.instance());
			this.logger.addHandler(this.fileerr);
		}
		catch (Exception e) {
			System.err.println("LOG: error while loading error file handler");
			System.err.println(e);
			e.printStackTrace();
		}
		try {
			this.stdout = new StreamHandler(System.out,new ResultFormatter(false));
			this.stdout.setLevel(this.stdoutlevel);
			this.logger.addHandler(this.stdout);
		}
		catch (Exception e) {
			System.err.println("LOG: error while loading output console handler");
			System.err.println(e);
			e.printStackTrace();
		}
		try {
			this.fileout = new FileHandler(FILENAME+"-"+
			            		          (System.currentTimeMillis() / 1000)+
			                   			  this.extout);
			this.fileout.setFormatter(new ResultFormatter(true));
			this.fileout.setLevel(this.fileoutlevel);
			this.logger.addHandler(this.fileout);
		}
		catch (Exception e) {
			System.err.println("LOG: error while loading error file handler");
			System.err.println(e);
			e.printStackTrace();
		}
	}

	private Logger              logger;
	private ConsoleHandler      stderr;
	private FileHandler         fileerr;
	private Level               stderrlevel  = Level.ALL;
	private Level               fileerrlevel = Level.ALL;
	private String              exterr       = ".log";

	private StreamHandler       stdout;
	private FileHandler         fileout;
	private Level               stdoutlevel  = RESULT;
	private Level               fileoutlevel = RESULT;
	private String              extout       = ".out";

	private static final Log    INSTANCE   = new Log();

	public static class DebugLevel extends Level {
		public static Level instance() {
			if (_instance == null)
				_instance = new DebugLevel();
			return _instance;
		}
		private static DebugLevel _instance = null;
		private DebugLevel() {
			super("DEBUG",Level.FINE.intValue());
		}
	};
	public static class ErrorLevel extends Level {
		public static Level instance() {
			if (_instance == null)
				_instance = new ErrorLevel();
			return _instance;
		}
		private static ErrorLevel _instance = null;
		private ErrorLevel() {
			super("ERROR",Level.SEVERE.intValue());
		}
	};
	public static class ResultLevel extends Level {
		public static Level instance() {
			if (_instance == null)
				_instance = new ResultLevel();
			return _instance;
		}
		private static ResultLevel _instance = null;
		private ResultLevel() {
			super("RESULT",ERROR.intValue()+1);
		}
	};

	private static class ErrFormatter extends Formatter {
		private boolean _alone = false;
		public ErrFormatter() { }
		public ErrFormatter(boolean alone) {
			_alone = alone;
		}
		@Override
		public String format(LogRecord record) {
			StringBuilder log = new StringBuilder();
			if (!_alone) {
				log.append('[');
				log.append(record.getLoggerName());
				log.append("] ");
			}

			log.append("[");
			log.append(this.levelOf(record));
			log.append("] ");

			log.append(record.getMillis());
			log.append(':');
			log.append(this.classOf(record));
			log.append(':');
			log.append(this.methodOf(record));
			
			log.append("\n<");
			log.append(record.getMessage());
			log.append(">\n");

			return log.toString();
		}
		public String levelOf(LogRecord record) {
			int l = record.getLevel().intValue();
			/*if (l <= DEBUG.intValue())
				return "DEBUG";
			if (l <= INFO.intValue())
				return "INFO";
			if (l <= WARNING.intValue())
				return "WARNING";
			if (l <= ERROR.intValue())
				return "ERROR";
			return "none";*/
			return record.getLevel().getName();
		}
		public String classOf(LogRecord record) {
			String s = record.getSourceClassName();
			if ((s == null) || (s.length() == 0))
				return "none";
			return s;
		}
		public String methodOf(LogRecord record) {
			String s = record.getSourceMethodName();
			if ((s == null) || (s.length() == 0))
				return "none";
			return s;
		}
	};

	private static class ResultFormatter extends Formatter {
		public ResultFormatter() {
			_alone = false;
		}
		public ResultFormatter(boolean alone) {
	   	   _alone = alone;
		}
		@Override
		public String format(LogRecord record) {
			StringBuilder log = new StringBuilder();
			if (!_alone) {
				log.append('[');
				log.append(record.getLoggerName());
				log.append("] ");
			}
			log.append(record.getMessage());
			log.append('\n');
			return log.toString();
		}
		private boolean _alone;
	};

	private static class NoResultFilter implements Filter {
		public static NoResultFilter instance() {
			if (_instance == null)
				_instance = new NoResultFilter();
			return _instance;
		}
		private static NoResultFilter _instance = null;
		@Override
		public boolean isLoggable(LogRecord record) {
			return (record.getLevel() != RESULT);
		}
		private NoResultFilter() { }
	}

};

