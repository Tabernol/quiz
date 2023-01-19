package app_listener;

import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class FinishServletListener implements ServletRequestListener {
    @Override
    public void requestInitialized(ServletRequestEvent sre) {
       // ServletRequestListener.super.requestInitialized(sre);
        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
        Integer count = 0;
        if (request.getServletPath().equals("/start_test")) {
            count++;
            System.out.println("Tests have started " + count);
        }
    }

//    public void count(ServletRequestEvent sre) {
//        HttpServletRequest request = (HttpServletRequest) sre.getServletRequest();
//        System.out.println(request.getRequestURI());
//        if (request.getParameter("percent_result") != null) {
//            System.out.println(request.getSession().getAttribute("name") + "has finished test with grade "
//                    + request.getParameter("percent_result"));
//        }
//    }
}
