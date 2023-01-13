import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.AnswerRepo;

import java.sql.SQLException;

class ExceptionTest {
    @Mock
    AnswerRepo mockAnswerRepo;

    @BeforeEach
    public void setUp() {
        mockAnswerRepo = Mockito.mock(AnswerRepo.class);
    }

    @Test
    public void constructorOneParameterTest() throws DataBaseException {

        Mockito.when(mockAnswerRepo.delete(Mockito.anyLong())).thenThrow(new DataBaseException("test message"));

        Exception exception = Assertions.assertThrows(DataBaseException.class, () -> {
            mockAnswerRepo.delete(Mockito.anyLong());
        });

        Assertions.assertEquals("test message", exception.getMessage());
    }

    @Test
    public void constructorTwoParameterTest() throws DataBaseException {

        Mockito.when(mockAnswerRepo.delete(Mockito.anyLong()))
                .thenThrow(new DataBaseException("test message", new SQLException()));

        Exception exception = Assertions.assertThrows(DataBaseException.class, () -> {
            mockAnswerRepo.delete(Mockito.anyLong());
        });

        Assertions.assertEquals("test message", exception.getMessage());
        Assertions.assertEquals(DataBaseException.class, exception.getClass());
    }


}
