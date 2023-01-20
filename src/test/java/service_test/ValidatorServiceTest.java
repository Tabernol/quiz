package service_test;

import exeptions.ValidateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import servises.ValidatorService;
import validator.DataValidator;

public class ValidatorServiceTest {
    private ValidatorService validatorService;
    @Mock
    private DataValidator mockDataValidator;

    @BeforeEach
    public void setUp() {
        mockDataValidator = Mockito.mock(DataValidator.class);
        validatorService = new ValidatorService();
    }

    @Test
    public void validateTextTest() throws ValidateException {
        Mockito.when(mockDataValidator.validateForNotLongString(Mockito.anyString())).thenReturn(true);
        Assertions.assertEquals(true, validatorService.validateText(Mockito.anyString()));
    }

}
