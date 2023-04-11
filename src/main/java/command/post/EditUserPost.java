package command.post;

import controllers.servlet.RequestHandler;
import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
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
 * EditUserPost.class is allowed only for admin.
 * The meaning of the class is to update User in database.
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditUserPost implements RequestHandler {
   private UserService userService = new UserService(new UserRepoImpl(), new ValidatorService(new DataValidator()));
    /**
     * This method is read parameter from request.
     * It calls the service layer to update this User
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
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
        String name = req.getParameter("name");
        String role = req.getParameter("role");


        try {
            userService.updateUser(new UserDto(userId, name, role));
            log.info("User with id " + userId + "changes made successfully");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/profile" +
                    "&user_id=" + userId +
                    "&name=" + name +
                    "&message_success=changes made successfully");
        } catch (DataBaseException e) {
            log.warn("User with id " + userId + "did not update");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            log.info("User with id " + userId + "input data is wrong");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/profile" +
                    "&user_id=" + userId +
                    "&name=" + name +
                    "&message_bad_request=" + e.getMessage());
        }
    }
}
