package command;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role =(String) req.getSession().getAttribute("role");
        if (role.equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/view/admin/admin_menu.jsp").forward(req, resp);
        } else if (role.equals("student")) {
            req.getRequestDispatcher("/WEB-INF/view/student/student_menu.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/").forward(req, resp);
        }
    }
}
