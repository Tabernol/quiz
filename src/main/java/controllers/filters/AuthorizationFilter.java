package controllers.filters;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
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
        final String login = req.getParameter(RequestHandler.LOGIN);
        final String password = req.getParameter(RequestHandler.PASSWORD);
        userService = AppContext.getInstance().getUserService();
        long id = -1;
        UserDto userDto = null;
        boolean isCorrectPassword = false;

        // get reCAPTCHA request param
        String gRecaptchaResponse = req
                .getParameter("g-recaptcha-response");

        boolean verify = VerifyRecaptcha.verify(gRecaptchaResponse);


        if (login.isEmpty() || password.isEmpty()) {
            log.info("login or password is null");
            req.setAttribute(RequestHandler.MESSAGE_BAD_REQUEST, "You may have forgotten to enter your input data");
            req.getRequestDispatcher(RequestHandler.LOGIN_FORM).forward(req, resp);
        }


        try {
            id = userService.getId(login);
            userDto = userService.get(id);
            isCorrectPassword = userService.isCorrectPassword(id, password);
        } catch (DataBaseException | ValidateException e) {
            log.warn("Problem in authorization filter");
            req.getRequestDispatcher(RequestHandler.ERROR_PAGE).forward(req, resp);
        }

        if (!verify) {
            log.info("User with id {} reCAPTCHA is false", userDto.getId());
            req.setAttribute(RequestHandler.MESSAGE_BAD_REQUEST, "reCAPTCHA is false");
            req.setAttribute(RequestHandler.REPEAT_LOGIN, login);
            req.getRequestDispatcher(RequestHandler.LOGIN_FORM).forward(req, resp);
        } else if (id == -1) {
            log.warn("This login = {} does not exist in database", login);
            req.setAttribute(RequestHandler.MESSAGE_BAD_REQUEST, "You do not registered");
            req.setAttribute(RequestHandler.REPEAT_LOGIN, login);
            req.getRequestDispatcher(RequestHandler.LOGIN_FORM).forward(req, resp);
        } else if (userDto.isBlocked()) {
            log.warn("User with id {} is block, and try login", userDto.getId());
            req.setAttribute(RequestHandler.MESSAGE_BAD_REQUEST, "You have been blocked");
            req.getRequestDispatcher(RequestHandler.LOGIN_FORM).forward(req, resp);
        } else if (isCorrectPassword) {
            log.info("User with id {} has come", userDto.getId());
            session.setAttribute(RequestHandler.USER_ID, id);// get id
            session.setAttribute(RequestHandler.NAME, userDto.getName());
            session.setAttribute(RequestHandler.ROLE, userDto.getRole());
            filterChain.doFilter(req, resp);
        } else {
            log.warn("User with id {} has input wrong password", userDto.getId());
            req.setAttribute(RequestHandler.REPEAT_LOGIN, login);
            req.setAttribute(RequestHandler.MESSAGE_BAD_REQUEST, "Something wrong(");
            req.getRequestDispatcher(RequestHandler.LOGIN_FORM).forward(req, resp);
        }
    }
}
