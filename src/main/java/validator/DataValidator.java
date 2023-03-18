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

    /**
     * Validates a login string based on a regular expression.
     *
     * @param login the login string to be validated
     * @return true if the login string matches the regular expression; false otherwise
     */
    public boolean validateLogin(String login) {
        return Pattern.matches(REGEX_LOGIN, login);
    }

    /**
     * Validates a password string based on a regular expression.
     *
     * @param password the password string to be validated
     * @return true if the password string matches the regular expression; false otherwise
     */
    public boolean validatePassword(String password) {
        return Pattern.matches(REGEX_PASSWORD, password);
    }

    /**
     * Validates a difficulty level based on minimum and maximum values.
     *
     * @param difficult the difficulty level of test(quiz)
     * @return true if the difficulty level is within the valid range; false otherwise
     */
    public boolean validateDifficult(Integer difficult) {
        return difficult >= MIN_DIFFICULT && difficult <= MAX_DIFFICULT;
    }

    /**
     * Validates a duration time based on minimum and maximum values.
     *
     * @param time the duration time of test(quiz)
     * @return true if the duration time is within the valid range; false otherwise
     */
    public boolean validateDuration(Integer time) {
        return time >= MIN_DURATION && time <= MAX_DURATION;
    }

    /**
     * Validates a string to ensure it is not too long based on a regular expression.
     *
     * @param string the string to be validated
     * @return true if the string matches the regular expression and is not too long; false otherwise
     */
    public boolean validateForNotLongString(String string) {
        return Pattern.matches(REGEX_NOT_LONG_STRING, string);
    }

    /**
     * Validates a string to ensure it conforms to a valid name format based on a regular expression.
     *
     * @param string the string to be validated as a name
     * @return true if the string matches the regular expression for a valid name format; false otherwise
     */
    public boolean validateForName(String string) {
        return Pattern.matches(REGEX_NAME, string);
    }

    /**
     * Validates a string to ensure it conforms to a valid name format based on a regular expression.
     *
     * @param string the string to be validated as a name
     * @return true if the string matches the regular expression for a valid name format; false otherwise
     */
    public boolean validateForNamePlusNumber(String string) {
        return Pattern.matches(REGEX_NAME_NUMBER, string);
    }

    /**
     * Validates a role string to ensure it is a valid value.
     *
     * @param role the role string to be validated
     * @return true if the role is either "admin" or "student"; false otherwise
     */
    public boolean validateAvailabilityRole(String role) {
        return role.equals("admin") || role.equals("student");
    }

    /**
     * Validates a boolean result and throws a ValidateException with a message if it is false.
     *
     * @param result  the boolean result to be validated
     * @param message the message to be included in the ValidateException if the result is false
     * @return true if the result is true
     * @throws ValidateException if the result is false, with the given message
     */
    @Override
    public boolean isValid(boolean result, String message) throws ValidateException {
        if (!result) {
            throw new ValidateException(message);
        }
        return true;
    }
}
