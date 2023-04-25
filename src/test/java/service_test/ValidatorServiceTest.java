package service_test;

import exeptions.ValidateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;
import validator.MyValidator;

public class ValidatorServiceTest {
    @Mock
    private DataValidator mockDataValidator;
    @Mock
    private MyValidator mockMyValidator;
    private ValidatorServiceImpl validatorService;

    @BeforeEach
    public void setUp() {
        mockMyValidator = Mockito.mock(MyValidator.class);
        mockDataValidator = Mockito.mock(DataValidator.class);
        validatorService = new ValidatorServiceImpl(mockDataValidator);
    }


    @Test
    public void isLoginThrowExTest() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString()))
                .thenThrow(new ValidateException("test"));
        Assertions.assertThrows(ValidateException.class, () -> validatorService.isLoginExist(false));
    }

    @Test
    public void repeatPassword() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> validatorService.validateRepeatPassword(Mockito.anyString(), Mockito.anyString()));
    }

    @Test
    public void validateTextTest() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> validatorService.validateText(Mockito.anyString()));
    }

    @Test
    public void validateFieldUserInputTest() throws ValidateException {
        Mockito.when(mockMyValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(
                () -> validatorService.validateFieldsUser("name", "login", "passwordHash"));
    }

    @Test
    public void validateUpdateUser1() throws ValidateException {
        Mockito.when(mockMyValidator.isValid(Mockito.anyBoolean(),
                Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> validatorService.validateUpdateUser(Mockito.anyString()));
    }

    @Test
    public void validateUpdateUser2() {
        Mockito.when(mockDataValidator.validateForName(Mockito.anyString())).thenReturn(true);
        Mockito.when(mockDataValidator.validateAvailabilityRole(Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() ->
                validatorService.validateUpdateUser("name", "student"));
    }

}

