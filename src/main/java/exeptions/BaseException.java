package exeptions;

public class BaseException extends RuntimeException {
    String parameters;

    public BaseException(String message, String parameters, Throwable cause) {
        super("Error - cannot " + message + ", " + parameters, cause);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }

}
