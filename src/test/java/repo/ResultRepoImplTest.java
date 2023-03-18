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

    private ResultRepoImpl resultRepoImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        resultRepoImpl = new ResultRepoImpl();
    }

    @Test
    public void AddResultTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
            Assertions.assertEquals(12, resultRepoImpl.addResult(11122L, 1234L, 45));
        }
    }

    @Test
    public void addResultThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> resultRepoImpl.addResult(11122L, 1234L, 45));
        }
    }

    @Test
    public void getCountResultByUserAndSubjectTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(true);
            Mockito.when(mockResultSet.getInt(1)).thenReturn(15);
            Assertions.assertEquals(15, resultRepoImpl.getCountResultByUserAndSubject(134L, "sub"));
        }
    }

    @Test
    public void getCountResultByUserAndSubjectThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> resultRepoImpl.getCountResultByUserAndSubject(134L, "sub"));
            Assertions.assertThrows(DataBaseException.class,
                    () -> resultRepoImpl.getCountResultByUser(34768L));
        }
    }

    @Test
    public void getPageResultListThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> resultRepoImpl.getPageResultList("sql"));
        }
    }

    @Test
    public void getAllResultTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(new ArrayList<ResultDto>(), resultRepoImpl.getAllResult(12L));
        }
    }

    @Test
    public void getAllResultThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> resultRepoImpl.getAllResult(12L));
        }
    }



}
