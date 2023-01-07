package exeptions;

public class QuizException extends Exception{
    private String message;
    private Throwable throwable;
    public QuizException(String message, Throwable cause) {
        super(message, cause);
    }
}
