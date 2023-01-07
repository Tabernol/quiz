package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.User;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditProfile implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        UserService userService = new UserService();
        User user;
        try {
            user = userService.get(userId);
            req.setAttribute("name", user.getName());
            req.setAttribute("login", user.getLogin());
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }
        req.getRequestDispatcher("WEB-INF/view/student/edit_profile.jsp").forward(req,resp);
    }
}
