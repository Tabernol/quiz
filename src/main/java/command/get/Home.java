package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Home implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");
        if (role == null) {
            req.getRequestDispatcher("/").forward(req, resp);
        } else if (role.equals("admin") || role.equals("student")) {
            req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);
        }
    }
}