package command.post;

import command.EditProfile;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserProfile implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        Long userId = (Long) req.getSession().getAttribute("user_id");

        UserService userService = new UserService();

        try {
            userService.updateUser(userId, name, login);
        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        }
        EditProfile editProfile = new EditProfile();
        editProfile.execute(req, resp);
    }
}
