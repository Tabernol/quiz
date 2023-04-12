package app_listener;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.HashMap;
import java.util.Map;

/**
 * A ServletContextListener that initializes the Cloudinary service for the application
 * by setting up the Cloudinary configuration and creating a Cloudinary instance to be stored as
 * a ServletContext attribute for use by other components.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@WebListener
@Slf4j
public class LoadMediaContextListener implements ServletContextListener {
    /**
     * Initializes the Cloudinary service for the application by setting up the Cloudinary configuration and creating
     * a Cloudinary instance to be stored as a ServletContext attribute for use by other components.
     *
     * @param sce the ServletContextEvent that triggered this initialization
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();

        Map config = new HashMap();

        String cloudName = servletContext.getInitParameter("CLOUD_NAME");
        String apiKey = servletContext.getInitParameter("API_KEY");
        String apiSecret = servletContext.getInitParameter("API_SECRET");

        config.put("cloud_name", cloudName);

        config.put("api_key", apiKey);

        config.put("api_secret", apiSecret);

        Cloudinary cloudinary = new Cloudinary(config);

        servletContext.setAttribute("cloudinary", cloudinary);
        log.info("Cloud load service initialized for Application.");

    }


}
