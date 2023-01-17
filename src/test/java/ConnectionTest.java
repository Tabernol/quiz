//import connection.InterfaceForDB;
//import connection.MyDataSource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.function.Executable;
//import org.mockito.Mockito;
//
//import java.sql.Connection;
//import java.sql.SQLException;
//
//public class ConnectionTest {
//   private static MyDataSource myDataSource;
//   InterfaceForDB interfaceForDB;
//   Connection connection;
//    @BeforeEach
//    public void setUp(){
//        MyDataSource interfaceForDB = (MyDataSource) Mockito.mock(InterfaceForDB.class);
//        Connection connection = Mockito.mock(Connection.class);
//    }
//    @Test
//    public void conTest() throws SQLException {
//        Mockito.when(interfaceForDB.getMyCon()).thenReturn(connection);
//        Assertions.assertNotNull(interfaceForDB.getMyCon());
//    }
//
//    @Test void closePool() throws SQLException {
//        MyDataSource.getConnection();
//        MyDataSource.closePool();
//        Assertions.assertThrows(SQLException.class, ()-> MyDataSource.getConnection());
//    }
//}
