package service_test;

import exeptions.ValidateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import servises.ValidatorService;
import validator.DataValidator;
import validator.MyValidator;

public class ValidatorServiceTest {
    @Mock
    DataValidator mockDataValidator;
    @Mock
    MyValidator mockMyValidator;
    ValidatorService validatorService;

    @BeforeEach
    public void setUp() {
        mockMyValidator = Mockito.mock(MyValidator.class);
        mockDataValidator = Mockito.mock(DataValidator.class);
        validatorService = new ValidatorService(mockDataValidator);
    }

    @Test
    public void isLoginExist() throws ValidateException {
        Mockito.when(mockMyValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertEquals(true, validatorService.isLoginExist(Mockito.anyBoolean()));
        // Assertions.assertDoesNotThrow(() -> validatorService.isLoginExist(Mockito.anyBoolean()));
    }

//    @Test
//    public void isLoginThrowExTest() throws ValidateException {
//        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(false);
//        Assertions.assertThrows(ValidateException.class, () -> validatorService.isLoginExist(true));
//    }

    @Test
    public void repeatPassword() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> validatorService.validateRepeatPassword(Mockito.anyString(), Mockito.anyString()));
    }

    @Test
    public void validateTextTest() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(()->validatorService.validateText(Mockito.anyString()));
    }

    @Test
    public void validateFieldUserInputTest() throws ValidateException {
        Mockito.when(mockMyValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(
                ()->validatorService.validateFieldsUser(Mockito.anyString(), Mockito.anyString(), Mockito.anyString()));
    }
}

