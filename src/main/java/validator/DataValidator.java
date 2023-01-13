package validator;

import java.util.regex.Pattern;

public class DataValidator {
    private static final String REGEX_LOGIN = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
            + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
    private static final String REGEX_PASSWORD = "^.{4,10}$";
    private static final String REGEX_NAME = "^[a-zA-ZА-Яа-я *]{2,20}$";
    private static final String REGEX_NAME_NUMBER = "^[0-9a-zA-ZА-Яа-я *]{2,20}$";

    private static final String REGEX_NOT_LONG_STRING = "^[\\s\\S]{1,20}$";
    private static final Integer MIN_DIFFICULT = 1;
    private static final Integer MAX_DIFFICULT = 100;
    private static final Integer MIN_DURATION = 1;
    private static final Integer MAX_DURATION = 30;


    public static boolean validateLogin(String login) {
        return Pattern.matches(REGEX_LOGIN, login);
    }

    public static boolean validatePassword(String password) {

        return Pattern.matches(REGEX_PASSWORD, password);
    }

//    public static boolean validateName(String name){
//        return Pattern.matches(REGEX_NAME, name);
//    }

    public static boolean validateDifficult(Integer difficult) {
        return difficult >= MIN_DIFFICULT && difficult <= MAX_DIFFICULT;
    }

    public static boolean validateDuration(Integer time) {
        return time >= MIN_DURATION && time <= MAX_DURATION;
    }

    public static boolean validateForNotLongString(String string) {
        return Pattern.matches(REGEX_NOT_LONG_STRING, string);
    }

    public static boolean validateForName(String string) {
        return Pattern.matches(REGEX_NAME, string);
    }

    public static boolean validateForNamePlusNumber(String string) {
        return Pattern.matches(REGEX_NAME_NUMBER, string);
    }

    public static boolean validateAvailabilityRole(String role) {
        return role.equals("admin") || role.equals("student");
    }


}
