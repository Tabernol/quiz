package command.post;

import command.AbstractCommand;
import context.AppContext;
import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.UserService;
import util.reCaptcha.VerifyRecaptcha;

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
public class Registration extends AbstractCommand {

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
        String name = req.getParameter(NAME);
        String login = req.getParameter(LOGIN);
        String password = req.getParameter(PASSWORD);
        String repeatPassword = req.getParameter(REPEAT_PASSWORD);

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if (verify) {
            try {
                Long userId = userService.createUser(UserDto.builder()
                                .login(login)
                                .name(name).build()
                        , password, repeatPassword);
                UserDto userDto = userService.get(userId);
                HttpSession session = req.getSession();
                session.setAttribute(USER_ID, userId);// get id
                session.setAttribute(NAME, userDto.getName());
                session.setAttribute(ROLE, userDto.getRole());
                log.info("User has created with login {}", login);
                req.getRequestDispatcher(MENU).forward(req, resp);
            } catch (DataBaseException e) {
                log.warn("User have not created", e.getMessage());
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            } catch (ValidateException e) {
                log.info("User field is invalid ", e.getMessage());
                req.setAttribute(MESSAGE_BAD_REQUEST, e.getMessage());
                setAttributesForRequest(req, NAME, LOGIN);
                req.getRequestDispatcher(REGISTRATION).forward(req, resp);
            } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
                log.warn("Problem with hash password ", e.getMessage());
                req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
            }
        } else {
            req.setAttribute(MESSAGE_BAD_REQUEST, "reCAPTCHA is false");
            setAttributesForRequest(req, NAME, LOGIN);
            req.getRequestDispatcher(REGISTRATION).forward(req, resp);
        }
    }
}
