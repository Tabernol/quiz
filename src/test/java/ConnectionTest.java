import connection.MyDataSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

public class ConnectionTest {
    @BeforeEach
    public void setUp(){
    }
    @Test
    public void conTest() throws SQLException {
        MyDataSource.init();
        Assertions.assertNotNull(MyDataSource.getConnection());
    }
}
