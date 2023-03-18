package exeptions;

public class QuizException extends Exception{
    public QuizException(String message, Throwable cause) {
        super(message, cause);
    }

    public QuizException(String message) {
        super(message);
    }
}
