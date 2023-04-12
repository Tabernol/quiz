package servises;

import dto.TestDto;
import exeptions.ValidateException;

public interface ValidatorService {
    void checkFieldsTest(TestDto testDto)
            throws ValidateException;

    void isNameExist(boolean isExist) throws ValidateException;

    boolean isLoginExist(boolean isExist) throws ValidateException;

    boolean validateText(String text) throws ValidateException;

    void validateFieldsUser(String name, String login, String password) throws ValidateException;

    void validateUpdateUser(String name) throws ValidateException;

    void validateUpdateUser(String name, String role) throws ValidateException;

    void validateRepeatPassword(String password, String repeatPassword) throws ValidateException;
}
