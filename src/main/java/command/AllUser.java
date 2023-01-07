package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.User;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUser implements RequestHandler {
    UserService userService = new UserService();
    @Override
    public void execute(HttpServletRequest req, 
                        HttpServletResponse resp) 
            throws ServletException, IOException {
        List<User> all;
        try {
            all = userService.getAll();
            req.setAttribute("users",all );
            req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req,resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }







    }
}
