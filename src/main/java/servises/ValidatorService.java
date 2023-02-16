package servises;

import exeptions.DataBaseException;
import exeptions.QuizException;
import exeptions.ValidateException;
import validator.DataValidator;
import validator.MyValidator;

import java.util.logging.Level;

public class ValidatorService {
    private DataValidator dataValidator;

    public ValidatorService(MyValidator myValidator) {
        this.dataValidator = (DataValidator) myValidator;
    }

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

    public void isNameExist(boolean isExist) throws ValidateException {
        dataValidator.isValid(!isExist, "Test with this name already exist");
    }

    public boolean isLoginExist(boolean isExist) throws ValidateException {
        return dataValidator.isValid(!isExist, "This login already exist");
    }

    public boolean validateText(String text) throws ValidateException {
        return dataValidator.isValid(dataValidator.validateForNotLongString(text), "Text answer is too long");
    }

    public void validateFieldsUser(String name, String login, String passwordHash) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateLogin(login),
                "login is invalid");
        dataValidator.isValid(dataValidator.validatePassword(passwordHash),
                "password is invalid must 4-10 symbols");
    }

    public void validateUpdateUser(String name) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
    }

    public void validateUpdateUser(String name, String role) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        dataValidator.isValid(dataValidator.validateAvailabilityRole(role),
                "Role must be 'admin' or 'student'");
    }

    public void validateRepeatPassword(String password, String repeatPassword) throws ValidateException {
        dataValidator.isValid(password.equals(repeatPassword), "password is not the same");
    }
}
