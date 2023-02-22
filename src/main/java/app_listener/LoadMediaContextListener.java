package app_listener;

import com.cloudinary.Cloudinary;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.util.HashMap;
import java.util.Map;

public class LoadMediaContextListener implements ServletContextListener {
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

        System.out.println("Cloud load service initialized for Application.");
    }
}
