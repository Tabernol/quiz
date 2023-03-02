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

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ImageRepoTest {
    @Mock
    private Connection mockConnection;
    @Mock
    private PreparedStatement mockPreparedStatement;
    @Mock
    private ResultSet mockResultSet;

    private ImageRepo imageRepo;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        imageRepo = new ImageRepo();
    }

    @Test
    public void testAddImage() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            int image = imageRepo.addImage("publicID", "URL", 600, 600);
            Assertions.assertEquals(13, image);
        }
    }

    @Test
    public void testAddImageThrowException() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertThrows(DataBaseException.class,
                    () -> imageRepo.addImage("publicID", "URL", 600, 600));
        }
    }

    @Test
    public void testDeleteImage() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            int count = imageRepo.deleteImage("publicID");
            Assertions.assertEquals(13, count);
        }
    }

    @Test
    public void testDeleteImageThrowException() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenThrow(new SQLException());
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            Assertions.assertThrows(DataBaseException.class,
                    () -> imageRepo.deleteImage("publicID"));
        }
    }

    @Test
    public void testThrowGetAllImages() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Mockito.when(mockResultSet.next()).thenReturn(false);
            assertThrows(DataBaseException.class, () -> imageRepo.getAll());
        }
    }
}
