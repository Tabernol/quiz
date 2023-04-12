package servises.impl;

import dto.TestDto;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.ValidatorService;
import validator.DataValidator;

/**
 * This class validate input data
 */
@Slf4j
public class ValidatorServiceImpl implements ValidatorService {
    private DataValidator dataValidator;

    public ValidatorServiceImpl(DataValidator dataValidator) {
        this.dataValidator = dataValidator;
    }

    /**
     * This method calls DataValidator.class to validate each input parameter
     *
     * @param testDto where:
     *                name  is name of test(quiz)
     *                subject is topic of test
     *                difficult is difficult of test must be from 0 to 100
     *                duration is duration of test must be from 1 to 30 minutes
     * @throws ValidateException
     */
    public void checkFieldsTest(TestDto testDto)
            throws ValidateException {
        dataValidator.isValid(dataValidator.validateForNamePlusNumber(testDto.getName()),
                "name must contains only liters and numbers and space from 2-20 symbols");
        log.info("SERVICE VALIDATOR name is valid");
        dataValidator.isValid(dataValidator.validateForName(testDto.getSubject()),
                "subject must contains only liters and space from 2-20 symbols");
        log.info("SERVICE VALIDATOR subject is valid");
        dataValidator.isValid(dataValidator.validateDifficult(testDto.getDifficult()),
                "difficult must be from 1 to 100");
        log.info("SERVICE VALIDATOR difficult is valid");
        dataValidator.isValid(dataValidator.validateDuration(testDto.getDuration()),
                "duration must be from 1 to 30 minutes");
        log.info("SERVICE VALIDATOR duration is valid");
    }

    /**
     * the target of this method throw exception if parameter true.
     *
     * @param isExist exist this name in database or no
     * @throws ValidateException
     */
    public void isNameExist(boolean isExist) throws ValidateException {
        dataValidator.isValid(!isExist, "Test with this name already exist");
        log.info("SERVICE VALIDATOR name is valid");
    }

    /**
     * the target of this method throw exception if parameter true.
     *
     * @param isExist
     * @return
     * @throws ValidateException
     */
    public boolean isLoginExist(boolean isExist) throws ValidateException {
        return dataValidator.isValid(!isExist, "This login already exist");
    }

    /**
     * This method calls to DataValidator.class to validate text or throw exception
     *
     * @param text is input text for validation
     * @return true or throw Validate exception
     * @throws ValidateException
     */
    public boolean validateText(String text) throws ValidateException {
        boolean textAnswerIsTooLong = dataValidator.isValid(dataValidator.validateForNotLongString(text),
                "Text answer is too long");
        log.info("SERVICE VALIDATOR text is long " + textAnswerIsTooLong);
        return textAnswerIsTooLong;
    }

    /**
     * This method calls to DataValidator to check all input fields or throw exception.
     * This method helps during registration new user
     *
     * @param name     is user`s name
     * @param login    is user`s login
     * @param password is user`s password
     * @throws ValidateException
     */
    public void validateFieldsUser(String name, String login, String password) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        log.info("SERVICE VALIDATOR name is valid");
        dataValidator.isValid(dataValidator.validateLogin(login),
                "login is invalid");
        log.info("SERVICE VALIDATOR login is valid");
        dataValidator.isValid(dataValidator.validatePassword(password),
                "password is invalid must 4-10 symbols");
        log.info("SERVICE VALIDATOR password is valid");
    }

    /**
     * This method calls to DataValidator to check new name of user
     *
     * @param name is new user`s name
     * @throws ValidateException
     */
    public void validateUpdateUser(String name) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        log.info("SERVICE VALIDATOR name is valid");
    }

    /**
     * This method calls to DataValidator to check new name of user an role
     *
     * @param name is new user`s name
     * @param role is new user`s role
     * @throws ValidateException
     */
    public void validateUpdateUser(String name, String role) throws ValidateException {
        dataValidator.isValid(dataValidator.validateForName(name),
                "name must contains only liters and numbers and space from 2-20 symbols");
        log.info("SERVICE VALIDATOR name is valid");
        dataValidator.isValid(dataValidator.validateAvailabilityRole(role),
                "Role must be 'admin' or 'student'");
        log.info("SERVICE VALIDATOR role is valid");
    }

    /**
     * This method equals to parameter
     *
     * @param password       is user`s password
     * @param repeatPassword is user`s password repeat
     * @throws ValidateException
     */
    public void validateRepeatPassword(String password, String repeatPassword) throws ValidateException {
        dataValidator.isValid(password.equals(repeatPassword), "password is not the same");
        log.info("SERVICE VALIDATOR passwords is valid");
    }
}
