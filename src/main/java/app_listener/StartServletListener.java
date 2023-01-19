package app_listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class StartServletListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        Integer count = 0;
        if (request.getServletPath().equals("/start_test")) {
            count++;
            System.out.println("Tests have started " + count);
        }
    }
}
