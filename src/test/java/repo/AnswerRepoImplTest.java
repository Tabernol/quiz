package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Answer;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.AnswerRepoImpl;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

class AnswerRepoImplTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private AnswerRepoImpl answerRepoImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        answerRepoImpl = new AnswerRepoImpl();
    }


    @Test
    public void testCreateAnswer() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            int answer = answerRepoImpl.createAnswer(256863L, "new", true);
//            int answer = answerRepo.createAnswer(Mockito.anyLong(), Mockito.anyString(), Mockito.anyBoolean());
            Assertions.assertEquals(13, answer);
        }

    }

    @Test
    public void testThrowExceptionCreateAnswer() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertThrows(DataBaseException.class,
                    () -> answerRepoImpl.createAnswer(256863L, "new", true));
        }
    }

    @Test
    public void testGetAnswerByQuestionId() throws Exception {
        List<Answer> answerListTest = new ArrayList<>();
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            List<Answer> answersByQuestionId = answerRepoImpl.getAnswersByQuestionId(1L);
            assertEquals(answerListTest, answersByQuestionId);
        }
    }

    @Test
    public void testThrowAnswerByQuestionId() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Mockito.when(mockResultSet.next()).thenReturn(false);
            assertThrows(DataBaseException.class, () -> answerRepoImpl.getAnswersByQuestionId(1232L));
        }
    }

    @Test
    public void testThrowDeleteAnswer() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class, () -> answerRepoImpl.delete(2342L));
        }
    }
}
