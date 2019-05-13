package az.pashabank.posemu;

import javax.swing.WindowConstants;
import org.apache.commons.lang.exception.ExceptionUtils;

public class EventLog {
    
    public enum LogLevel {
        NONE(0),
        ERROR(1),
        WARNING(2),
        INFO(3),
        DEBUG(4),
        UNKNOWN(-1);
        
        private final int value;
        
        private LogLevel (int value) {
            this.value = value;
        }
        
        public static LogLevel getLogLevel (String logLevel) {
            switch (logLevel) {
                case "NONE": return NONE;
                case "ERROR": return ERROR;
                case "WARNING": return WARNING;
                case "INFO": return INFO;
                case "DEBUG": return DEBUG;
                default: return UNKNOWN;
            }
        } 
        
        public int getValue () {
            return this.value;
        }
        
        @Override
        public String toString () {
            switch (this) {
                case NONE: return "[NONE ]";
                case ERROR: return "[ERROR]";
                case WARNING: return "[WARN ]";
                case INFO: return "[INFO ]";
                case DEBUG: return "[DEBUG]";
                default: return "[-----]";
            }
        }
    }
    
    private LogLevel logLevel = LogLevel.INFO;
    
    private static DialogEventLog dialogEventLog;
    private static EventLog eventLog;
    
    static EventLog getInstance () {
        if (dialogEventLog == null) {
            dialogEventLog = new DialogEventLog(null, false);
            dialogEventLog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            eventLog = new EventLog();
        }
        return eventLog;
    }
    
    public void setLogLevel (LogLevel logLevel) {
        this.logLevel = logLevel;
    }
    
    void setVisible (boolean visible) {
        dialogEventLog.setVisible(visible);
    } 
    
    boolean isVisible () {
        return dialogEventLog.isVisible();
    }
    
    void info (String msg) {
        log(LogLevel.INFO, msg);
    }
    
    void warn (String msg) {
        log(LogLevel.WARNING, msg);
    }
    
    void error (String msg) {
        log(LogLevel.ERROR, msg);
    }
    
    void error (String msg, Throwable t) {
        log(LogLevel.ERROR, msg, t);
    }
    
    void debug (String msg) {
        log(LogLevel.DEBUG, msg);
    }
    
    private void log (LogLevel logLevel, String msg) {
        log(logLevel, msg, null);
    }
    
    private void log (LogLevel logLevel, String msg, Throwable t) {
        if (logLevel.getValue() <= this.logLevel.getValue()) {
            dialogEventLog.log(Utils.getDateTime() + " " + this.logLevel.toString() + " " + msg + "\n");
            if (t != null) {
                dialogEventLog.log(ExceptionUtils.getStackTrace(t) + "\n");
            }
        }
    }
    
}
