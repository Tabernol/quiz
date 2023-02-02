package service_test;

import exeptions.ValidateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import servises.ValidatorService;
import validator.DataValidator;

public class ValidatorServiceTest {
    @Mock
    DataValidator mockDataValidator;
    ValidatorService validatorService;

    @BeforeEach
    public void setUp() {
        mockDataValidator = Mockito.mock(DataValidator.class);
        validatorService = new ValidatorService();
    }

    @Test
    public void isLoginExist() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> validatorService.isLoginExist(false));
    }

//    @Test
//    public void isLoginThrowExTest() throws ValidateException {
//        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(false);
//        Assertions.assertThrows(ValidateException.class, () -> validatorService.isLoginExist(true));
//    }

    @Test
    public void repeatPassword() throws ValidateException {
        Mockito.when(mockDataValidator.isValid(Mockito.anyBoolean(), Mockito.anyString())).thenReturn(true);
        Assertions.assertDoesNotThrow(() -> validatorService.validateRepeatPassword(Mockito.anyString(),Mockito.anyString()));
    }
}

