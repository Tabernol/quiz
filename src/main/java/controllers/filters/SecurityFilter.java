package controllers.filters;

import controllers.servlet.RequestHandler;
import lombok.extern.slf4j.Slf4j;
import controllers.AccessUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "SecurityFilter", value = "/*")
@Slf4j
public class SecurityFilter extends AbstractFilter {
    private static final  String ACCESS_DENIED = "/WEB-INF/view/access.jsp";

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain)
            throws IOException, ServletException {
        String role = (String) req.getSession().getAttribute(ROLE);
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        String servletPath = req.getServletPath();


        if (role == null) {
            if (AccessUtil.guestAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                req.getRequestDispatcher("/").forward(req, resp);
            }

        } else if (role.equals(STUDENT)) {
            if (AccessUtil.studentAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                log.warn("User with id {} uses no correct path", userId);
                req.getRequestDispatcher(ACCESS_DENIED).forward(req, resp);
            }


        } else if (role.equals(ADMIN)) {
            if (AccessUtil.adminAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                log.warn("User-admin with {} uses no correct path", userId);
                req.getRequestDispatcher(ACCESS_DENIED).forward(req, resp);
            }
        }
    }
}
