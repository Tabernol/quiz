package controllers.filters;

import connection.MyDataSource;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.PasswordHashingService;
import servises.UserService;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

import org.apache.logging.log4j.LogManager;
import servises.ValidatorService;
import util.VerifyRecaptcha;
import validator.DataValidator;

//@WebFilter(filterName = "AuthorizationFilter", value = "/login")
public class AuthorizationFilter extends AbstractFilter {
    private static Logger logger = LogManager.getLogger(AuthorizationFilter.class);

    UserService userService;

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        final HttpSession session = req.getSession();
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        userService = new UserService(new UserRepo(), new ValidatorService(new DataValidator()));
        long id = -1;
        User user = null;
        boolean isCorrectPassword = false;

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println("reCaptcha = " + gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);

        try {
            id = userService.getId(login);
            user = userService.get(id);
            isCorrectPassword = userService.isCorrectPassword(id, password);
        } catch (DataBaseException | ValidateException e) {
            logger.warn("Problem in authorization filter");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


        if (id == -1) {
            req.setAttribute("message", "You do not registered");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (user.isBlocked()) {
            logger.warn("User with id " + user.getId() + "is block, and try login");
            req.setAttribute("message", "You are blocked");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (isCorrectPassword && verify) {
            logger.info("User with id " + user.getId() + " has come");
            session.setAttribute("user_id", id);// get id
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRole().getRole());
            filterChain.doFilter(req, resp);

        } else {
            logger.warn("User with id " + user.getId() + "has input wrong password");
            req.setAttribute("message", "Something wrong(");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }
    }
}
