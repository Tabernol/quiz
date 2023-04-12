package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Question;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.QuestionRepoImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class QuestionRepoImplTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private DataSource mockDataSource;

    private QuestionRepo questionRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = Mockito.mock(DataSource.class);
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        questionRepo = new QuestionRepoImpl(mockDataSource);
    }

    @Test
    public void testCreateQuestion() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        long rowsUpdate = questionRepo.create(new Question(121L, 1234L, "text", "url"));
        Assertions.assertEquals(13L, rowsUpdate);
    }

    @Test
    public void testUpdateQuestion() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        int rowsUpdate = questionRepo.update(new Question(123L, 23L, "test text", "url"));
        Assertions.assertEquals(13, rowsUpdate);
    }

    @Test
    public void testDeleteQuestion() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        int rowsUpdate = questionRepo.delete(323L);
        Assertions.assertEquals(13, rowsUpdate);
    }

    @Test
    public void testGetQuestionByTestId() throws Exception {
        List<Question> questionList = new ArrayList<>();
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(false);
        List<Question> allById = questionRepo.getAllById(342L);
        assertEquals(questionList, allById);
    }

    @Test
    public void testThrowQuestionByTestId() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> questionRepo.getAllById(34467L));
    }

    @Test
    public void getQuestionThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> questionRepo.get(34467L));
    }

    @Test
    public void deleteQuestionThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> questionRepo.delete(34467L));
    }

    @Test
    public void updateQuestionThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () ->
                questionRepo.update(new Question(12L, 123L, "new text", "url")));
    }

    @Test
    public void CreateQuestionThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> questionRepo.create(new Question(12L, 123L, "new text", "url")));
    }

    @Test
    public void testUpdateImageQuestion() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        int rowsUpdate = questionRepo.updateImageQuestion("URL", 323L);
        Assertions.assertEquals(13, rowsUpdate);
    }

    @Test
    public void updateImageQuestionThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> questionRepo.updateImageQuestion("URL", 34467L));
    }
}
