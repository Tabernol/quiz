package command;

import controllers.servlet.RequestHandler;
import models.User;
import servises.UserService;

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
        Long userId = (Long) req.getSession().getAttribute("user_id");
        System.out.println("user id = " + userId);
        UserService userService = new UserService();
        User user = userService.get(userId);
        req.setAttribute("name", user.getName());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());
        req.getRequestDispatcher("/WEB-INF/view/student/profile.jsp").forward(req, resp);

    }
}
