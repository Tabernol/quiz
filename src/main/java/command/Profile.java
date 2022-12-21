package command;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Profile implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession();
        String role = (String) session.getAttribute("role");
        System.out.println("role =  " + role);
        if (role != null) {
            if (role.equals("admin")) {
                req.getRequestDispatcher("/WEB-INF/view/admin/admin_menu.jsp").forward(req, resp);
            } else if (role.equals("student")) {
                req.getRequestDispatcher("/WEB-INF/view/student/student_menu.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }

    }
}
