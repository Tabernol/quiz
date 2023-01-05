package dao.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import util.LoaderProperty;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class MyDataSource {
    // private static final String CONFIG_FILE = "dataSource.properties";
    private static HikariConfig config; //Static is better or not?
    private static HikariDataSource ds; //Static is better or not?

    private static final String configFile = "src/main/resources/hikari.properties";

    public static void init() {
       // Properties properties = LoaderProperty.get("hikari.properties");

        config = new HikariConfig();

//        config.setJdbcUrl(properties.getProperty("jdbcUrl"));
//        config.setUsername(properties.getProperty("username"));
//        config.setPassword(properties.getProperty("password"));
//        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//        config.addDataSourceProperty("cachePrepStmts",
//                properties.getProperty("cachePrepStmts"));
//        config.addDataSourceProperty("prepStmtCacheSize",
//                properties.getProperty("prepStmtCacheSize"));
//        config.addDataSourceProperty("prepStmtCacheSqlLimit",
//                properties.getProperty("prepStmtCacheSqlLimit"));

        config.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/epam_project_testing");
        config.setUsername("root");
        config.setPassword("root");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.addDataSourceProperty("cachePrepStmts", "true");
        config.addDataSourceProperty("prepStmtCacheSize", "250");
        config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        ds = new HikariDataSource(config);
    }

    private MyDataSource() {
    }

    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }

    public static void closePool(){
        ds.close();
    }
}
