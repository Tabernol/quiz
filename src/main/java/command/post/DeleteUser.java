package command.post;

import controllers.servlet.RequestHandler;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService();
        String user_id = req.getParameter("user_id");
        userService.deleteUser(Long.valueOf(user_id));
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req,resp);
    }
}
