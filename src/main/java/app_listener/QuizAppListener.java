package app_listener;

import dao.connection.MyDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.SQLException;
@WebListener
public class QuizAppListener implements ServletContextListener {
    private final Logger LOGGER = LogManager.getLogger(QuizAppListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        LOGGER.info("Webapp 'Quiz Service' was started.");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        MyDataSource.closePool();
        LOGGER.info("Webapp 'Quiz service' was closed");
    }
}
