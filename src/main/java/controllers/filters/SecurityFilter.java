package controllers.filters;

import command.get.LanguageChange;
import constans.PathConst;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import util.AccessUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "SecurityFilter", value = "/*")
public class SecurityFilter extends AbstractFilter {
    private static Logger logger = LogManager.getLogger(SecurityFilter.class);

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain)
            throws IOException, ServletException {
        String role = (String) req.getSession().getAttribute("role");
        Long userId = (Long) req.getSession().getAttribute("user_id");
        String servletPath = req.getServletPath();


        if (role == null) {
            System.out.println("role == null");
            if (AccessUtil.guestAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                req.getRequestDispatcher("/").forward(req, resp);
            }

        } else if (role.equals("student")) {
            if (AccessUtil.studentAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                logger.warn("User with id " + userId + " uses no correct path");
                req.getRequestDispatcher("/WEB-INF/view/access.jsp").forward(req, resp);
            }


        } else if (role.equals("admin")) {
            if (AccessUtil.adminAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                logger.warn("User-admin with id " + userId + " uses no correct path");
                req.getRequestDispatcher("/WEB-INF/view/access.jsp").forward(req, resp);
            }
        }
    }
}
