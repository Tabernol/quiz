package controllers.filters;

import constans.PathConst;
import util.AccessUtil;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//@WebFilter(filterName = "SecurityFilter", value = "/*")
public class SecurityFilter extends AbstractFilter {
    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain)
            throws IOException, ServletException {
        String role = (String) req.getSession().getAttribute("role");
        String servletPath = req.getServletPath();

        System.out.println(role);



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
            } else req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);


        } else if (role.equals("admin")) {
            if (AccessUtil.adminAccess.contains(servletPath)) {
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);
            }
        }
    }
}
