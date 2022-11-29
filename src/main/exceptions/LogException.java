package exceptions;

/**
 * Represents the exception that can occur when
 * printing the event log.
 */

// sourced from CPSC 210 AlarmSystem
public class LogException extends Exception {
    public LogException() {
        super("Error printing log");
    }

    public LogException(String msg) {
        super(msg);
    }
}