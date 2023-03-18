package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.UserRepoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserRepoImplTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;
    private UserRepoImpl userRepoImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        userRepoImpl = new UserRepoImpl();
    }

    @Test
    public void getAllThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> userRepoImpl.getAll());
        }
    }

    @Test
    public void createUserTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            mockConnection.setAutoCommit(false);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
         //   Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(true);
            Mockito.when(mockResultSet.getInt("last_insert_id()")).thenReturn(123);
            Assertions.assertEquals(123, userRepoImpl.createUser("login", "password", "name"));
        }
    }

    @Test
    public void createUserThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> userRepoImpl.createUser("login", "password", "name"));
        }
    }

    @Test
    public void isLoginExistTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(true);
            Assertions.assertEquals(true, userRepoImpl.isLoginExist("login"));
        }
    }

    @Test
    public void isLoginExistThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> userRepoImpl.isLoginExist("login"));
        }
    }

    @Test
    public void updateUserTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
            Assertions.assertEquals(12, userRepoImpl.updateUser(12312L, "name"));
        }
    }

    @Test
    public void updateUserThrowEx() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class,
                    () -> userRepoImpl.updateUser(1234L, "name"));
            Assertions.assertThrows(DataBaseException.class,
                    () -> userRepoImpl.updateUser(1234L, "name", "student"));
        }
    }

    @Test
    public void updateUserFullTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
            Assertions.assertEquals(12, userRepoImpl.updateUser(1234L, "name", "student"));
        }
    }

    @Test
    public void getIdTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(true);
            Mockito.when(mockResultSet.getLong("id")).thenReturn(123L);
            Assertions.assertEquals(123L, userRepoImpl.getId("login"));
        }
    }

    @Test
    public void getIdThrowExTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class, () -> userRepoImpl.getId("login"));
        }
    }


    @Test
    public void changeStatusTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
            Assertions.assertEquals(12, userRepoImpl.changeStatus(123L, false));
        }
    }

    @Test
    public void changeStatusThrowExTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class, () -> userRepoImpl.changeStatus(123L, true));
        }
    }

    @Test
    public void getCountUsersTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(true);
            Mockito.when(mockResultSet.getInt(1)).thenReturn(13);
            Integer countUsers = userRepoImpl.getCountUsers("true");
            Assertions.assertEquals(13, countUsers);
        }
    }

    @Test
    public void getCountUsersThrow() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class, () -> userRepoImpl.getCountUsers("true"));
        }
    }

    @Test
    public void nextPageTest() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
            Mockito.when(mockResultSet.next()).thenReturn(false);
            Assertions.assertEquals(new ArrayList<>(), userRepoImpl.nextPage("query"));
        }
    }

    @Test
    public void nextPageThrow() throws SQLException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Assertions.assertThrows(DataBaseException.class, () -> userRepoImpl.nextPage("query"));
        }
    }
}