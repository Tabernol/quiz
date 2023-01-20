package exeptions;

public class ValidateException extends QuizException{

    public ValidateException(String message, Throwable cause) {
        super(message, cause);
    }

    public ValidateException(String message) {
        super(message);
    }
}
