package validator;

import java.util.regex.Pattern;

public class DataValidator {

    private static final String REGEX_PASSWORD = "^.{4,10}$";

    private static final String REGEX_NOT_LONG_STRING = "^[ A-Za-zА-Яа-я]{1,30}$";
    private static final Integer MIN_DIFFICULT = 1;
    private static final Integer MAX_DIFFICULT = 100;
    private static final Integer MIN_DURATION = 1;
    private static final Integer MAX_DURATION = 30;


    public static boolean validatePassword(String password) {

        return Pattern.matches(REGEX_PASSWORD, password);
    }

    public static boolean validateDifficult(Integer difficult) {
        return difficult >= MIN_DIFFICULT && difficult <= MAX_DIFFICULT;
    }

    public static boolean validateDuration(Integer time) {
        return time >= MIN_DURATION && time <= MAX_DURATION;
    }

    public static boolean validateForNotLongString(String string) {
        return Pattern.matches(REGEX_NOT_LONG_STRING, string);
    }
//
    public static void main(String[] args) {
        System.out.println(validateForNotLongString("sdfg hdf g"));
    }


}
