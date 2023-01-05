package controllers.filters;

import dao.connection.MyDataSource;
import servises.UserService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "AuthorizationFilter", value = "/login")
public class AuthorizationFilter extends AbstractFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        MyDataSource.init();
    }

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        final HttpSession session = req.getSession();//what first??
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");// or this first??
        String lang = req.getParameter("lang");
        UserService userService = new UserService();
        String role = "unknown";

        long id = userService.getId(login);
        if (id == -1) {
            req.setAttribute("message", "You do not registred");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (userService.isBlocked(id)) {
            req.setAttribute("message", "You are blocked");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (userService.isCorrectPassword(id, password)) {
            role = userService.getRoleById(id);
            session.setAttribute("user_id", id);// get id
            session.setAttribute("name", userService.get(id).getName());
            session.setAttribute("role", userService.get(id).getRole());
            filterChain.doFilter(req, resp);
            // moveToMenu(req, resp, role);
        } else {
            req.setAttribute("message", "Something wrong(");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }

    }

//    private void moveToMenu(final HttpServletRequest req,
//                            final HttpServletResponse res,
//                            final String role)
//            throws ServletException, IOException {
//
//        if (role.equals("admin")) {
//            req.getRequestDispatcher("/WEB-INF/view/admin/admin_menu.jsp").forward(req, res);
//        } else if (role.equals("student")) {
//            req.getRequestDispatcher("/WEB-INF/view/student/student_menu.jsp").forward(req, res);
//        } else {
//            req.getRequestDispatcher("/").forward(req, res);
//        }
//    }
}
