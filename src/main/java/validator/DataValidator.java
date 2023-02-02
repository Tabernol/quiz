package validator;

import exeptions.QuizException;
import exeptions.ValidateException;

import java.util.regex.Pattern;

public class DataValidator implements MyValidator {
    private static final String REGEX_LOGIN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_PASSWORD = "^.{4,10}$";
    private static final String REGEX_NAME = "^[a-zA-Zа-щА-ЩЬьЮюЯяЇїІіЄєҐґ *]{2,20}$";
    private static final String REGEX_NAME_NUMBER = "^[0-9a-zA-Zа-щА-ЩЬьЮюЯяЇїІіЄєҐґ *]{2,20}$";

    private static final String REGEX_NOT_LONG_STRING = "^[\\s\\S]{1,20}$";
    private static final Integer MIN_DIFFICULT = 1;
    private static final Integer MAX_DIFFICULT = 100;
    private static final Integer MIN_DURATION = 1;
    private static final Integer MAX_DURATION = 30;


    public boolean validateLogin(String login) {
        return Pattern.matches(REGEX_LOGIN, login);
    }

    public boolean validatePassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    public boolean validateDifficult(Integer difficult) {
        return difficult >= MIN_DIFFICULT && difficult <= MAX_DIFFICULT;
    }

    public boolean validateDuration(Integer time) {
        return time >= MIN_DURATION && time <= MAX_DURATION;
    }

    public boolean validateForNotLongString(String string) {
        return Pattern.matches(REGEX_NOT_LONG_STRING, string);
    }

    public boolean validateForName(String string) {
        return Pattern.matches(REGEX_NAME, string);
    }

    public boolean validateForNamePlusNumber(String string) {
        return Pattern.matches(REGEX_NAME_NUMBER, string);
    }

    public boolean validateAvailabilityRole(String role) {
        return role.equals("admin") || role.equals("student");
    }


//    public static boolean validate(boolean check, String message) throws ValidateException {
//        if (!check) {
//            throw new ValidateException(message);
//        }
//        return true;
//    }

    @Override
    public boolean isValid(boolean result, String message) throws ValidateException {
        if (!result) {
            throw new ValidateException(message);
        }
        return true;
    }
}
