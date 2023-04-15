package command.post;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.UserRepoImpl;
import servises.UserService;
import servises.impl.UserServiceImpl;
import servises.impl.ValidatorServiceImpl;
import util.reCaptcha.VerifyRecaptcha;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * Registration.class is responsible for passing parameters to create a user.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class Registration implements RequestHandler {

    /**
     * This method is read parameter from request.
     * It checks the parameter "g-recaptcha-response" and allows registration or not
     * It calls the service layer to create new User
     * if DataBaseException or NoSuchAlgorithmException or InvalidKeySpecException
     * is caught, redirects to error page.
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
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeat_password");

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println("reCaptcha = " + gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if (verify) {
            try {
                Long userId = userService.createUser(UserDto.builder()
                        .login(login)
                        .name(name).build()
                        , password, repeatPassword);
                UserDto userDto = userService.get(userId);
                HttpSession session = req.getSession();
                session.setAttribute("user_id", userId);// get id
                session.setAttribute("name", userDto.getName());
                session.setAttribute("role", userDto.getRole());
                log.info("User has created with login {}", login);
                req.getRequestDispatcher(MENU).forward(req, resp);
            } catch (DataBaseException e) {
                log.warn("User have not created", e.getMessage());
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            } catch (ValidateException e) {
                log.info("User field is invalid ", e.getMessage());
                req.setAttribute("message_bad_request", e.getMessage());
                req.setAttribute("repeat_name", name);
                req.setAttribute("repeat_login", login);
                req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.warn("Problem with hash password ", e.getMessage());
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            }
        } else {
            req.setAttribute("message_bad_request", "reCAPTCHA is false");
            req.setAttribute("repeat_name", name);
            req.setAttribute("repeat_login", login);
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
        }
    }
}
