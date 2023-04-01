package controllers.filters;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.impl.UserRepoImpl;
import servises.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import servises.ValidatorService;
import util.reCaptcha.VerifyRecaptcha;
import validator.DataValidator;

//@WebFilter(filterName = "AuthorizationFilter", value = "/login")
public class AuthorizationFilter extends AbstractFilter {
    private static Logger logger = LogManager.getLogger(AuthorizationFilter.class);
    private UserService userService;

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        final HttpSession session = req.getSession();
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        userService = new UserService(new UserRepoImpl(), new ValidatorService(new DataValidator()));
        long id = -1;
        User user = null;
        boolean isCorrectPassword = false;

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println("reCaptcha = " + gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);


        if (login.isEmpty() || password.isEmpty()) {
            logger.info("login or password is null");
            req.setAttribute("message_bad_request", "You may have forgotten to enter your input data");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }


        try {
            id = userService.getId(login);
            user = userService.get(id);
            isCorrectPassword = userService.isCorrectPassword(id, password);
        } catch (DataBaseException | ValidateException e) {
            logger.warn("Problem in authorization filter");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }

        if (!verify) {
            logger.info("User with id " + user.getId() + " reCAPTCHA is false");
            req.setAttribute("message_bad_request", "reCAPTCHA is false");
            req.setAttribute("repeat_login", login);
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (id == -1) {
            logger.warn("This login = " + login + " does not exist in database");
            req.setAttribute("message_bad_request", "You do not registered");
            req.setAttribute("repeat_login", login);
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (user.isBlocked()) {
            logger.warn("User with id " + user.getId() + "is block, and try login");
            req.setAttribute("message_bad_request", "You have been blocked");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (isCorrectPassword) {
            logger.info("User with id " + user.getId() + " has come");
            session.setAttribute("user_id", id);// get id
            session.setAttribute("name", user.getName());
            session.setAttribute("role", user.getRole().getRole());
            filterChain.doFilter(req, resp);
        } else {
            logger.warn("User with id " + user.getId() + "has input wrong password");
            req.setAttribute("repeat_login", login);
            req.setAttribute("message_bad_request", "Something wrong(");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }
    }
}
