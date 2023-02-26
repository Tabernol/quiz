package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestRepoTest {
    @Mock
    Connection mockConnection;
    @Mock
    PreparedStatement mockPreparedStatement;
    @Mock
    ResultSet mockResultSet;

    TestRepo testRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        testRepo = new TestRepo();
    }

    @Test
    public void updateInfoTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13,
                    testRepo.updateInfoTest(1231L,
                            "name text", "name Subject", 23, 12));
        }
    }

    @Test
    public void updateInfoTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.updateInfoTest(1231L,
                            "name text", "name Subject", 23, 12));
        }
    }

    @Test
    public void getCountTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(true);
            Mockito.when(mockResultSet.getInt(1)).thenReturn(20);
            Assertions.assertEquals(20, testRepo.getCount());
            Assertions.assertEquals(20, testRepo.getCount("math"));
        }
    }

    @Test
    public void getCountTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.getCount("sub"));
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.getCount());
        }
    }

    @Test
    public void nextPageTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(new ArrayList<>(), testRepo.nextPage("sql"));
        }
    }

    @Test
    public void nextPageThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.nextPage("sub"));
        }
    }

    @Test
    public void getTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(null, testRepo.get(12413L));
        }
    }

    @Test
    public void getThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.get(23141L));
        }
    }

    @Test
    public void addPopularityTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13, testRepo.addPopularity(12413L));
        }
    }

    @Test
    public void addPopularityThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.addPopularity(23141L));
        }
    }


    @Test
    public void isNameExistThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.isNameExist("name"));
        }
    }

    @Test
    public void deleteTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13, testRepo.delete(12413L));
        }
    }

    @Test
    public void deleteTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.delete(324L));
        }
    }

    @Test
    public void createTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13,
                    testRepo.createTest("nameTEST", "subject", 45, 12));
        }
    }

    @Test
    public void createTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepo.createTest("nameTEST", "subject", 45, 12));
        }
    }

}
