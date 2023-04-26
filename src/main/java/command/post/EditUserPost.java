package command.post;

import command.AbstractCommand;
import context.AppContext;
import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EditUserPost.class is allowed only for admin.
 * The meaning of the class is to update User in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditUserPost extends AbstractCommand {

    /**
     * This method is read parameter from request.
     * It calls the service layer to update this User
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
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
        String name = req.getParameter(NAME);
        String role = req.getParameter(ROLE);


        try {
            userService.update(UserDto.builder()
                    .id(userId)
                    .name(name)
                    .role(role)
                    .build());
            log.info("User with id {} changes made successfully", userId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/profile" +
                    "&user_id=" + userId +
                    "&name=" + name +
                    "&message_success=changes made successfully");
        } catch (DataBaseException e) {
            log.warn("User with id {} did not update", userId);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (ValidateException e) {
            log.info("User with id {} input data is wrong", userId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/profile" +
                    "&user_id=" + userId +
                    "&name=" + name +
                    "&message_bad_request=" + e.getMessage());
        }
    }
}
