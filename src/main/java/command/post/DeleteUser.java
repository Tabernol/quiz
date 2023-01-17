package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteUser implements RequestHandler {

    private static Logger logger = LogManager.getLogger(DeleteUser.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService(new UserRepo());
        Long userId = Long.valueOf(req.getParameter("user_id"));
        try {
            userService.deleteUser(Long.valueOf(userId));
            req.setAttribute("users", userService.getAll());
            logger.info("User with id " + userId + "has deleted");
            req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            logger.info("User with id " + userId + "has not deleted");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }

    }
}
