package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.TestRepoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestRepoTestImpl {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private TestRepoImpl testRepoImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        testRepoImpl = new TestRepoImpl();
    }

    @Test
    public void updateInfoTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13,
                    testRepoImpl.updateInfoTest(
                            new models.Test(12L, "name", "subject",
                                    80, 25, 234, models.Test.Status.FREE)));
        }
    }

    @Test
    public void updateInfoTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.updateInfoTest(
                            new models.Test(12L, "name", "subject",
                                    23, 23, 100, models.Test.Status.FREE)));
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
            Assertions.assertEquals(20, testRepoImpl.getCount());
            Assertions.assertEquals(20, testRepoImpl.getCount("math"));
        }
    }

    @Test
    public void getCountTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.getCount("sub"));
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.getCount());
        }
    }

    @Test
    public void nextPageTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(new ArrayList<>(), testRepoImpl.nextPage("sql"));
        }
    }

    @Test
    public void nextPageThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.nextPage("sub"));
        }
    }

    @Test
    public void getTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(null, testRepoImpl.get(12413L));
        }
    }

    @Test
    public void getThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.get(23141L));
        }
    }

    @Test
    public void addPopularityTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13, testRepoImpl.addPopularity(12413L));
        }
    }

    @Test
    public void addPopularityThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.addPopularity(23141L));
        }
    }


    @Test
    public void isNameExistThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.isNameExist("name"));
        }
    }

    @Test
    public void deleteTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13, testRepoImpl.delete(12413L));
        }
    }

    @Test
    public void deleteTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.delete(324L));
        }
    }

    @Test
    public void createTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13,
                    testRepoImpl.create(new models.Test("nameTEST", "subject", 45, 12)));
        }
    }

    @Test
    public void createTestThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.create(new models.Test("nameTEST", "subject", 45, 12)));
        }
    }

    @Test
    public void changeStatusTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertEquals(13, testRepoImpl.changeStatus(12413L, models.Test.Status.FREE));
        }
    }

    @Test
    public void changeStatusThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> testRepoImpl.changeStatus(123L, models.Test.Status.BLOCKED));
        }
    }

    @Test
    public void getDistinctSubjectTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(new ArrayList<>(), testRepoImpl.getDistinctSubject());
        }
    }

    @Test
    public void getDistinctSubjectThrowEx() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class, () -> testRepoImpl.getDistinctSubject());
        }
    }

}
