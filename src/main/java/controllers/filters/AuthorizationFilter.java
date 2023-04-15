package controllers.filters;

import controllers.AppContext;
import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.UserRepoImpl;
import servises.UserService;
import servises.impl.UserServiceImpl;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import servises.impl.ValidatorServiceImpl;
import util.reCaptcha.VerifyRecaptcha;
import validator.DataValidator;

//@WebFilter(filterName = "AuthorizationFilter", value = "/login")
@Slf4j
public class AuthorizationFilter extends AbstractFilter {
    private UserService userService;

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        final HttpSession session = req.getSession();
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        userService = AppContext.getInstance().getUserService();
        long id = -1;
        UserDto userDto = null;
        boolean isCorrectPassword = false;

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");
        System.out.println("reCaptcha = " + gRecaptchaResponse);
        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);


        if (login.isEmpty() || password.isEmpty()) {
            log.info("login or password is null");
            req.setAttribute("message_bad_request", "You may have forgotten to enter your input data");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }


        try {
            id = userService.getId(login);
            userDto = userService.get(id);
            isCorrectPassword = userService.isCorrectPassword(id, password);
        } catch (DataBaseException | ValidateException e) {
            log.warn("Problem in authorization filter");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }

        if (!verify) {
            log.info("User with id {} reCAPTCHA is false", userDto.getId());
            req.setAttribute("message_bad_request", "reCAPTCHA is false");
            req.setAttribute("repeat_login", login);
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (id == -1) {
            log.warn("This login = {} does not exist in database", login);
            req.setAttribute("message_bad_request", "You do not registered");
            req.setAttribute("repeat_login", login);
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (userDto.isBlocked()) {
            log.warn("User with id {} is block, and try login", userDto.getId());
            req.setAttribute("message_bad_request", "You have been blocked");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        } else if (isCorrectPassword) {
            log.info("User with id {} has come", userDto.getId());
            session.setAttribute("user_id", id);// get id
            session.setAttribute("name", userDto.getName());
            session.setAttribute("role", userDto.getRole());
            filterChain.doFilter(req, resp);
        } else {
            log.warn("User with id {} has input wrong password", userDto.getId());
            req.setAttribute("repeat_login", login);
            req.setAttribute("message_bad_request", "Something wrong(");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }
    }
}
