package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Image;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.ImageRepoImpl;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageRepoImplTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;
    @Mock
    private DataSource mockDataSource;

    private ImageRepo imageRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockDataSource = Mockito.mock(DataSource.class);
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        imageRepo = new ImageRepoImpl(mockDataSource);
    }

    @Test
    public void testAddImage() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        int image = imageRepo.addImage(new Image(12L, 123L, "publicID", "url", 300,300));
        Assertions.assertEquals(13, image);
    }

    @Test
    public void testAddImageThrowException() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        Assertions.assertThrows(DataBaseException.class,
                () -> imageRepo.addImage(new Image()));
    }

    @Test
    public void testDeleteImage() throws SQLException, DataBaseException {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        int count = imageRepo.deleteImage("publicID");
        Assertions.assertEquals(13, count);
    }

    @Test
    public void testDeleteImageThrowException() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
        Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
        Assertions.assertThrows(DataBaseException.class,
                () -> imageRepo.deleteImage("publicID"));
    }

    @Test
    public void testThrowGetAllImages() throws Exception {
        Mockito.when(mockDataSource.getConnection()).thenReturn(mockConnection);
        Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
        Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
        Mockito.when(mockResultSet.next()).thenReturn(false);
        assertThrows(DataBaseException.class, () -> imageRepo.getAll());
    }
}
