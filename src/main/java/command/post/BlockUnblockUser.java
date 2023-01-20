package command.post;

import command.get.AllUser;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.UserService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class BlockUnblockUser implements RequestHandler {

    private static Logger logger = LogManager.getLogger(BlockUnblockUser.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService(new UserRepo(), new ValidatorService());

        Long userId = Long.valueOf(req.getParameter("user_id"));

        try {
            boolean block = userService.blockUnBlockUser(userId);
            logger.info("User with id " + userId + "is block " + block);
            resp.sendRedirect(req.getContextPath() + "/users");
        } catch (DataBaseException e) {
            logger.warn("User with id " + userId + "has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
