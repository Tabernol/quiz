import connection.MyDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import java.sql.SQLException;

public class ConnectionTest {
    @BeforeEach
    public void setUp(){
    }
    @Test
    public void conTest() throws SQLException {
        MyDataSource.getConnection();
        Assertions.assertNotNull(MyDataSource.getConnection());
    }

    @Test void closePool() throws SQLException {
        MyDataSource.getConnection();
        MyDataSource.closePool();
        Assertions.assertThrows(SQLException.class, ()-> MyDataSource.getConnection());
    }
}
