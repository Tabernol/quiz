package app_listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
@Slf4j
public class StartServletListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();

        if (request.getServletPath().equals("/start_test")) {
            log.info("Test has stared");
        }
    }
}
