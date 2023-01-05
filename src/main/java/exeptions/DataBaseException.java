package exeptions;

public class DataBaseException extends RuntimeException {
    public DataBaseException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
