package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Answer;
import org.junit.jupiter.api.*;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.AnswerRepoImpl;


import javax.sql.DataSource;
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
    @Mock
    private DataSource mockDataSource;

    private AnswerRepo answerRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = Mockito.mock(DataSource.class);
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        answerRepo = new AnswerRepoImpl(mockDataSource);
    }


    @Test
    public void testCreateAnswer() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        long answer = answerRepo.create(new Answer(123L, 256863L, "new", true));
        Assertions.assertEquals(13L, answer);
    }

    @Test
    public void testThrowExceptionCreateAnswer() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        Assertions.assertThrows(DataBaseException.class,
                () -> answerRepo.create(new Answer(123L, 256863L, "new", true)));
    }

    @Test
    public void testGetAnswerByQuestionId() throws Exception {
        List<Answer> answerListTest = new ArrayList<>();
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(false);
        List<Answer> answersByQuestionId = answerRepo.getAnswersByQuestionId(1L);
        assertEquals(answerListTest, answersByQuestionId);
    }

    @Test
    public void testThrowAnswerByQuestionId() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        Mockito.when(mockResultSet.next()).thenReturn(false);
        assertThrows(DataBaseException.class, () -> answerRepo.getAnswersByQuestionId(1232L));
    }

    @Test
    public void testThrowDeleteAnswer() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> answerRepo.delete(2342L));
    }
}
