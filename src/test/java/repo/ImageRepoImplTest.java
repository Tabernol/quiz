package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import repo.impl.ImageRepoImpl;

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

    private ImageRepoImpl imageRepoImpl;

    @BeforeEach
    public void setUp() throws SQLException {
        mockConnection = Mockito.mock(Connection.class);
        mockPreparedStatement = Mockito.mock(PreparedStatement.class);
        mockResultSet = Mockito.mock(ResultSet.class);
        imageRepoImpl = new ImageRepoImpl();
    }

    @Test
    public void testAddImage() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            int image = imageRepoImpl.addImage("publicID", "URL", 600, 600);
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
                    () -> imageRepoImpl.addImage("publicID", "URL", 600, 600));
        }
    }

    @Test
    public void testDeleteImage() throws SQLException, DataBaseException {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeUpdate()).thenReturn(13);
            int count = imageRepoImpl.deleteImage("publicID");
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
                    () -> imageRepoImpl.deleteImage("publicID"));
        }
    }

    @Test
    public void testThrowGetAllImages() throws Exception {
        try (MockedStatic<MyDataSource> myDataSourceMockedStatic = Mockito.mockStatic(MyDataSource.class)) {
            myDataSourceMockedStatic.when(() -> MyDataSource.getConnection()).thenReturn(mockConnection);
            Mockito.when(mockConnection.prepareStatement(Mockito.anyString())).thenReturn(mockPreparedStatement);
            Mockito.when(mockPreparedStatement.executeQuery()).thenThrow(new SQLException());
            Mockito.when(mockResultSet.next()).thenReturn(false);
            assertThrows(DataBaseException.class, () -> imageRepoImpl.getAll());
        }
    }
}
