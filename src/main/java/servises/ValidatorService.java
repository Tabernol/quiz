package servises;

import exeptions.DataBaseException;
import exeptions.QuizException;
import exeptions.ValidateException;
import validator.DataValidator;
import validator.MyValidator;

import java.util.logging.Level;

/**
 * This class validate input data
 */
public class ValidatorService {
    private DataValidator dataValidator;

    public ValidatorService(MyValidator myValidator) {
        this.dataValidator = (DataValidator) myValidator;
    }

    /**
     * This method calls DataValidator.class to validate each input parameter
     * @param name is name of test(quiz)
     * @param subject is topic of test
     * @param difficult is difficult of test must be from 0 to 100
     * @param duration is duration of test must be from 1 to 30 minutes
     * @throws ValidateException
     */
    public void checkFieldsTest(String name, String subject, Integer difficult, Integer duration)
            throws ValidateException {
        dataValidator.isValid(dataValidator.validateForNamePlusNumber(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateForNamePlusNumber(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateForName(subject),
                "subject must contains only liters and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateDifficult(difficult),
                "difficult must be from 1 to 100");
        dataValidator.isValid(dataValidator.validateDuration(duration),
                "duration must be from 1 to 30 minutes");
    }

    /**
     * the target of this method throw exception if parameter true.
     * @param isExist exist this name in database or no
     * @throws ValidateException
     */
    public void isNameExist(boolean isExist) throws ValidateException {
        dataValidator.isValid(!isExist, "Test with this name already exist");
    }

    /**
     * the target of this method throw exception if parameter true.
     * @param isExist
     * @return
     * @throws ValidateException
     */
    public boolean isLoginExist(boolean isExist) throws ValidateException {
        return dataValidator.isValid(!isExist, "This login already exist");
    }

    /**
     * This method calls to DataValidator.class to validate text or throw exception
     * @param text is input text for validation
     * @return true or throw Validate exception
     * @throws ValidateException
     */
    public boolean validateText(String text) throws ValidateException {
        return dataValidator.isValid(dataValidator.validateForNotLongString(text), "Text answer is too long");
    }

    /**
     * This method calls to DataValidator to check all input fields or throw exception.
     * This method helps during registration new user
     * @param name is user`s name
     * @param login is user`s login
     * @param password is user`s password
     * @throws ValidateException
     */
    public void validateFieldsUser(String name, String login, String password) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateLogin(login),
                "login is invalid");
        dataValidator.isValid(dataValidator.validatePassword(password),
                "password is invalid must 4-10 symbols");
    }

    /**
     * This method calls to DataValidator to check new name of user
     * @param name is new user`s name
     * @throws ValidateException
     */
    public void validateUpdateUser(String name) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
    }

    /**
     * This method calls to DataValidator to check new name of user an role
     * @param name is new user`s name
     * @param role is new user`s role
     * @throws ValidateException
     */
    public void validateUpdateUser(String name, String role) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateAvailabilityRole(role),
                "Role must be 'admin' or 'student'");
    }

    /**
     * This method equals to parameter
     * @param password is user`s password
     * @param repeatPassword is user`s password repeat
     * @throws ValidateException
     */
    public void validateRepeatPassword(String password, String repeatPassword) throws ValidateException {
        dataValidator.isValid(password.equals(repeatPassword), "password is not the same");
    }
}
