package command.post;

import command.get.EditUser;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
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

public class EditUserPost implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditUserPost.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        Boolean status = Boolean.valueOf(req.getParameter("status"));

        EditUser editUser = new EditUser();
        UserService userService = new UserService(new UserRepo(), new ValidatorService());

        if (!DataValidator.validateAvailabilityRole(role)) {
            req.setAttribute("user_id", userId);
            req.setAttribute("message", "Role must be 'admin' or 'student'");
            logger.info("User with id " + userId + "Role must be 'admin' or 'student'");
            editUser.execute(req, resp);
        } else if (!DataValidator.validateForName(name)) {
            req.setAttribute("message", "name is invalid");
            req.setAttribute("user_id", userId);
            logger.info("User with id " + userId + "name is invalid");
            editUser.execute(req, resp);
        } else {
            try {
                int i = userService.updateUser(userId, name, role, status);
                req.setAttribute("user_id", userId);
                req.setAttribute("message", "changes made successfully");
                logger.info("User with id " + userId + "changes made successfully");
                editUser.execute(req, resp);
            } catch (DataBaseException e) {
                logger.warn("User with id " + userId + "did not update");
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
        }


    }
}
