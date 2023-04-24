package app_listener;

import com.cloudinary.Cloudinary;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import java.util.HashMap;
import java.util.Map;

public class LoadMediaContextListenerTest {
    @Mock
    private ServletContextEvent mockServletContextEvent;
    @Mock
    private ServletContext mockServletContext;

    private LoadMediaContextListener listener;
    @Test
    public void testContextInitialized() {
        Mockito.mock(ServletContextEvent.class);
        Mockito.mock(ServletContext.class);
        Mockito.when(mockServletContextEvent.getServletContext()).thenReturn(mockServletContext);
        Mockito.when(mockServletContext.getInitParameter("CLOUD_NAME")).thenReturn("your_cloud_name");
        Mockito.when(mockServletContext.getInitParameter("API_KEY")).thenReturn("your_api_key");
        Mockito.when(mockServletContext.getInitParameter("API_SECRET")).thenReturn("your_api_secret");




        Map<String, Object> config = new HashMap<>();
        config.put("CLOUD_NAME", mockServletContext.getInitParameter("CLOUD_NAME"));
        config.put("API_KEY", mockServletContext.getInitParameter("API_KEY"));
        config.put("API_SECRET", mockServletContext.getInitParameter("API_SECRET"));

        Cloudinary cloudinary = new Cloudinary(config);
        mockServletContext.setAttribute("cloudinary", cloudinary);

        listener = new LoadMediaContextListener();
        listener.contextInitialized(mockServletContextEvent);




//        Assertions.assertEquals(cloudinary, (Cloudinary) servletContext.getAttribute("cloudinary"));
       // Assertions.assertNotNull(cloudinary);
//        Assertions.assertEquals("your_cloud_name", cloudinary.config.cloudName);
//        Assertions.assertEquals("your_api_key", cloudinary.config.apiKey);
//        Assertions.assertEquals("your_api_secret", cloudinary.config.apiSecret);
    }
}
