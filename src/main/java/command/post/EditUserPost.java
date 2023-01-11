package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.UserRepo;
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

        UserService userService = new UserService(new UserRepo());

        try {
            int i = userService.updateUser(userId, name, login, role, status);
            //   req.setAttribute("users", userService.getAll());
            req.setAttribute("user_id", userId);
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_user.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
