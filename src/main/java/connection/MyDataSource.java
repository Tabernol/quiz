package connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@Slf4j
public class MyDataSource {
    private static DataSource dataSource;
    private static HikariConfig config;

    private static void init() {
        config = new HikariConfig();

        try (InputStream input = MyDataSource.class.getClassLoader().getResourceAsStream("hikari.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find config.properties");
                return;
            }
            Properties properties = new Properties();
            properties.load(input);

            config.setJdbcUrl(properties.getProperty("jdbcUrl"));
            config.setUsername(properties.getProperty("username"));
            config.setPassword(properties.getProperty("password"));
            config.setDriverClassName(properties.getProperty("driver.class.name"));

            dataSource = new HikariDataSource(config);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private MyDataSource() {
    }

    public static DataSource getInstance(){
        if(dataSource==null){
            init();
        }
        return dataSource;
    }

}
