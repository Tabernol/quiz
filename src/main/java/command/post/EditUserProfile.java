package command.post;

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

        try {
            userService.updateUser(userId, name);
            logger.info("User with id " + userId + "changes made successfully");
            resp.sendRedirect(req.getContextPath() +
                    "/prg?servlet_path=/profile&message=" +
                    " changes made successfully");
        } catch (ValidateException e) {
            logger.warn("User with id " + userId + "name is invalid");
            resp.sendRedirect(req.getContextPath() +
                    "/prg?servlet_path=/profile&message=" +
                    " name is invalid");
        } catch (DataBaseException e) {
            logger.warn("User with id " + userId + "did not update");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}



