package command.post;


import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.UserRepoImpl;
import servises.UserService;
import servises.ValidatorService;
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
public class BlockUnblockUser implements RequestHandler {
    UserService userService = new UserService(new UserRepoImpl(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to change status user (block/unblock)
     * if DataBaseException is caught, redirects to error page.
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        String page = req.getParameter("page");

        try {
            boolean block = userService.blockUnBlockUser(userId);
            log.info("User with id " + userId + "is block " + block);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_users"+
                    "&page=" + page);
        } catch (DataBaseException e) {
            log.warn("User with id " + userId + "has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
