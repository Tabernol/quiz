package command.post;

import controllers.servlet.RequestHandler;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserPost implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        Boolean status = Boolean.valueOf(req.getParameter("status"));

        System.out.println("role  =  "+role);

        UserService userService = new UserService();
        userService.updateUser(userId, name, login, password, role, status);

        req.setAttribute("users", userService.getAll());
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req,resp);

    }
}
