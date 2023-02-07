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

public class EditUserPost implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditUserPost.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        String name = req.getParameter("name");
        String role = req.getParameter("role");

        UserService userService = new UserService(new UserRepo(), new ValidatorService(new DataValidator()));

        try {
            userService.updateUser(userId, name, role);
            logger.info("User with id " + userId + "changes made successfully");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/profile" +
                    "&user_id=" + userId +
                    "&name=" + name +
                    "&message=changes made successfully");
        } catch (DataBaseException e) {
            logger.warn("User with id " + userId + "did not update");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("User with id " + userId + "input data is wrong");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/profile" +
                    "&user_id=" + userId +
                    "&name=" + name +
                    "&message=" + e.getMessage());
        }
    }
}
