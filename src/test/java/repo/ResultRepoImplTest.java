package repo;

import connection.MyDataSource;
import dto.ResultDto;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.ResultRepoImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ResultRepoImplTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private DataSource mockDataSource;

    private ResultRepo resultRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = Mockito.mock(DataSource.class);
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        resultRepo = new ResultRepoImpl(mockDataSource);
    }

    @Test
    public void AddResultTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
        Assertions.assertEquals(12, resultRepo.addResult(11122L, 1234L, 45));
    }

    @Test
    public void addResultThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> resultRepo.addResult(11122L, 1234L, 45));
    }

    @Test
    public void getCountResultByUserAndSubjectTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(15);
        Assertions.assertEquals(15, resultRepo.getCountResultByUserAndSubject(134L, "sub"));
    }

    @Test
    public void getCountResultByUserAndSubjectThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> resultRepo.getCountResultByUserAndSubject(134L, "sub"));
        Assertions.assertThrows(DataBaseException.class,
                () -> resultRepo.getCountResultByUser(34768L));
    }

    @Test
    public void getPageResultListThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> resultRepo.getPageResultList("sql"));
    }

    @Test
    public void getAllResultTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(false);
        Assertions.assertEquals(new ArrayList<ResultDto>(), resultRepo.getAllResult(12L));
    }

    @Test
    public void getAllResultThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> resultRepo.getAllResult(12L));
    }

    @Test
    public void getDistinctSubjectTrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> resultRepo.getDistinctSubject(12L));
    }


}
