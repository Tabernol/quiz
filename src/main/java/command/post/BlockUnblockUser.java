package command.post;


import command.AbstractCommand;
import controllers.AppContext;
import controllers.PathConst;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.UserRepoImpl;
import servises.UserService;
import servises.impl.UserServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * BlockUnblockUser.class is allowed only for admin.
 * it keeps responsibility for block and block user
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class BlockUnblockUser extends AbstractCommand {

    /**
     * This method is read parameter from request.
     * It calls the service layer to change status user (block/unblock)
     * if DataBaseException is caught, redirects to error page.
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = AppContext.getInstance().getUserService();
        Long userId = Long.valueOf(req.getParameter(USER_ID));
        String page = req.getParameter(PAGE);

        try {
            boolean block = userService.blockUnBlockUser(userId);
            log.info("User with id {} is block {}", userId, block);
            resp.sendRedirect(req.getContextPath() + PathConst.FILTER_USERS +
                    "?page=" + page);
        } catch (DataBaseException e) {
            log.warn("User with id {} has not updated", userId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
