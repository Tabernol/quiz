package command.post;

import command.AllUser;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.UserService;

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
        UserService userService = new UserService(new UserRepo());

        Long userId = Long.valueOf(req.getParameter("user_id"));
        User user;
        try {
            user = userService.get(userId);
            if (userService.isBlocked(userId)) {
                userService.updateUser(userId, user.getName(), user.getRole(), false);
                logger.info("User with id " + userId + "has unblocked");
            } else {
                userService.updateUser(userId, user.getName(), user.getRole(), true);
                logger.info("User with id " + userId + "has blocked");
            }
            AllUser allUser = new AllUser();
            allUser.execute(req, resp);
        } catch (DataBaseException e) {
            logger.warn("User with id " + userId + "has not updated");
            throw new RuntimeException(e);
        }


    }
}
