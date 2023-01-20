package command.post;

import command.get.EditProfile;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.UserService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUserProfile implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditUserProfile.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        Long userId = (Long) req.getSession().getAttribute("user_id");

        UserService userService = new UserService(new UserRepo(), new ValidatorService());
        EditProfile editProfile = new EditProfile();
        DataValidator dataValidator = new DataValidator();


        try {
            userService.updateUser(userId, name);
            logger.info("User with id " + userId + "changes made successfully");
            resp.sendRedirect(req.getContextPath()+"/profile");

        } catch (DataBaseException e) {
            throw new RuntimeException(e);
        } catch (ValidateException e) {
            throw new RuntimeException(e);
        }


        if (!dataValidator.validateForName(name)) {
            req.setAttribute("message", "name is invalid");
            req.setAttribute("user_id", userId);
            logger.info("User with id " + userId + "name is invalid");
            editProfile.execute(req, resp);
        } else {
            try {
                userService.updateUser(userId, name);
                req.setAttribute("message", "changes made successfully");
                req.getSession().setAttribute("name", name);
                logger.info("User with id " + userId + "changes made successfully");
                editProfile.execute(req, resp);
            } catch (DataBaseException e) {
                logger.warn("User with id " + userId + "did not update");
                throw new RuntimeException(e);
            } catch (ValidateException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
