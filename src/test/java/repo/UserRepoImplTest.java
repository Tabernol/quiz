package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.UserRepoImpl;

import javax.sql.DataSource;
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
    @Mock
    private DataSource mockDataSource;
    private UserRepoImpl userRepo;

    User testUser;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = Mockito.mock(DataSource.class);
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        userRepo = new UserRepoImpl(mockDataSource);
        testUser = new User();
        testUser.setId(1245L);
        testUser.setName("name");
        testUser.setLogin("login");
        testUser.setRole(User.Role.ADMIN);
        testUser.setBlocked(false);
    }

    @Test
    public void getAllThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> userRepo.getAll());
    }

    @Test
    public void createUserTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        mockConnection.setAutoCommit(false);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true);
        Mockito.when(mockResultSet.getInt("last_insert_id()")).thenReturn(123);
        Assertions.assertEquals(123, userRepo.create(new User("login", "password", "name")));
    }

    @Test
    public void createUserThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> userRepo.create(new User("login", "password", "name")));
    }

    @Test
    public void isLoginExistTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true);
        Assertions.assertEquals(true, userRepo.isLoginExist("login"));
    }

    @Test
    public void isLoginExistThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> userRepo.isLoginExist("login"));
    }

    @Test
    public void updateUserTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
        Assertions.assertEquals(12, userRepo.update(testUser));
    }

    @Test
    public void updateUserThrowEx() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class,
                () -> userRepo.update(testUser));
        Assertions.assertThrows(DataBaseException.class,
                () -> userRepo.update(testUser));
    }

    @Test
    public void updateUserFullTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
        Assertions.assertEquals(12, userRepo.update(testUser));
    }

    @Test
    public void getIdTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true);
        Mockito.when(mockResultSet.getLong("id")).thenReturn(123L);
        Assertions.assertEquals(123L, userRepo.getId("login"));
    }

    @Test
    public void getIdThrowExTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> userRepo.getId("login"));
    }


    @Test
    public void changeStatusTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(12);
        Assertions.assertEquals(12, userRepo.changeStatus(123L, false));
    }

    @Test
    public void changeStatusThrowExTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> userRepo.changeStatus(123L, true));
    }

    @Test
    public void getCountUsersTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(true);
        Mockito.when(mockResultSet.getInt(1)).thenReturn(13);
        Integer countUsers = userRepo.getCountUsers("true");
        Assertions.assertEquals(13, countUsers);
    }

    @Test
    public void getCountUsersThrow() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> userRepo.getCountUsers("true"));
    }

    @Test
    public void nextPageTest() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);
        Mockito.when(mockResultSet.next()).thenReturn(false);
        Assertions.assertEquals(new ArrayList<>(), userRepo.nextPage("query"));
    }

    @Test
    public void nextPageThrow() throws SQLException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        Assertions.assertThrows(DataBaseException.class, () -> userRepo.nextPage("query"));
    }
}