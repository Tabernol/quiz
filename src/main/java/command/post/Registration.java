package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.PasswordHashingService;
import servises.UserService;
import servises.ValidatorService;
import util.VerifyRecaptcha;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public class Registration implements RequestHandler {

    private static Logger logger = LogManager.getLogger(Registration.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String repeatPassword = req.getParameter("repeat_password");

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println("reCaptcha = "+gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        if(verify){
            UserService userService = new UserService(new UserRepo(), new ValidatorService(new DataValidator()));

            try {
                int userId = userService.createUser(name, login, password, repeatPassword);
                User user = userService.get(userId);
                HttpSession session = req.getSession();
                session.setAttribute("user_id", userId);// get id
                session.setAttribute("name", user.getName());
                session.setAttribute("role", user.getRole());
                logger.info("User has created with login " + login);
                req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);
            } catch (DataBaseException e) {
                logger.warn("User have not created", e.getMessage());
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            } catch (ValidateException e) {
                logger.info("User field is invalid ", e.getMessage());
                req.setAttribute("message", e.getMessage());
                req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
            } catch (NoSuchAlgorithmException e) {
                logger.warn("Problem with hash password ", e.getMessage());
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            } catch (InvalidKeySpecException e) {
                logger.warn("Problem with hash password ", e.getMessage());
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            }
        } else {
            req.setAttribute("message", "reCAPTCHA is not true");
            req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
        }
    }
}
