package app_listener;

import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

public class LoadMediaContextListenerTest {
    @Test
    public void testContextInitialized() {
        LoadMediaContextListener listener = new LoadMediaContextListener();

        ServletContext servletContext = Mockito.mock(ServletContext.class);
        Mockito.when(servletContext.getInitParameter("CLOUD_NAME")).thenReturn("your_cloud_name");
        Mockito.when(servletContext.getInitParameter("API_KEY")).thenReturn("your_api_key");
        Mockito.when(servletContext.getInitParameter("API_SECRET")).thenReturn("your_api_secret");

        ServletContextEvent servletContextEvent = Mockito.mock(ServletContextEvent.class);
        Mockito.when(servletContextEvent.getServletContext()).thenReturn(servletContext);

        listener.contextInitialized(servletContextEvent);

        Cloudinary cloudinary = (Cloudinary) servletContext.getAttribute("cloudinary");
       // Assertions.assertNotNull(cloudinary);
        Assertions.assertEquals("your_cloud_name", cloudinary.config.cloudName);
        Assertions.assertEquals("your_api_key", cloudinary.config.apiKey);
        Assertions.assertEquals("your_api_secret", cloudinary.config.apiSecret);
    }
}
