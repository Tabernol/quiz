package command.post;

import command.EditProfile;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.UserRepo;
import servises.UserService;
import validator.DataValidator;

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
        Long userId = (Long) req.getSession().getAttribute("user_id");

        UserService userService = new UserService(new UserRepo());
        EditProfile editProfile = new EditProfile();

        if (!DataValidator.validateForName(name)) {
            req.setAttribute("message", "name is invalid");
            req.setAttribute("user_id", userId);
            editProfile.execute(req, resp);
        } else {
            try {
                userService.updateUser(userId, name);
                req.setAttribute("message", "changes made successfully");
                req.getSession().setAttribute("name", name);
                editProfile.execute(req, resp);
            } catch (DataBaseException e) {
                throw new RuntimeException(e);
            }
        }





    }
}
