import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import repo.impl.AnswerRepoImpl;

import java.sql.SQLException;

class ExceptionTest {
    @Mock
    AnswerRepoImpl mockAnswerRepoImpl;

    @BeforeEach
    public void setUp() {
        mockAnswerRepoImpl = Mockito.mock(AnswerRepoImpl.class);
    }

    @Test
    public void constructorOneParameterTest() throws DataBaseException {

        Mockito.when(mockAnswerRepoImpl.delete(Mockito.anyLong())).thenThrow(new DataBaseException("test message"));

        Exception exception = Assertions.assertThrows(DataBaseException.class, () -> {
            mockAnswerRepoImpl.delete(Mockito.anyLong());
        });

        Assertions.assertEquals("test message", exception.getMessage());
    }

    @Test
    public void constructorTwoParameterTest() throws DataBaseException {

        Mockito.when(mockAnswerRepoImpl.delete(Mockito.anyLong()))
                .thenThrow(new DataBaseException("test message", new SQLException()));

        Exception exception = Assertions.assertThrows(DataBaseException.class, () -> {
            mockAnswerRepoImpl.delete(Mockito.anyLong());
        });

        Assertions.assertEquals("test message", exception.getMessage());
        Assertions.assertEquals(DataBaseException.class, exception.getClass());
    }


}
