package controllers.filters;

import context.AppContext;
import dto.UserDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.UserService;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


import util.reCaptcha.VerifyRecaptcha;

@Slf4j
public class AuthorizationFilter extends AbstractFilter {

    private UserService userService;

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        final HttpSession session = req.getSession();
        final String login = req.getParameter(LOGIN);
        final String password = req.getParameter(PASSWORD);
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
            req.setAttribute(MESSAGE_BAD_REQUEST, "You may have forgotten to enter your input data");
            req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
        }


        try {
            id = userService.getId(login);
            userDto = userService.get(id);
            isCorrectPassword = userService.isCorrectPassword(id, password);
        } catch (DataBaseException | ValidateException e) {
            log.warn("Problem in authorization filter");
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }

        if (!verify) {
            log.info("User with id {} reCAPTCHA is false", userDto.getId());
            req.setAttribute(MESSAGE_BAD_REQUEST, "reCAPTCHA is false");
            req.setAttribute(REPEAT_LOGIN, login);
            req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
        } else if (id == -1) {
            log.warn("This login = {} does not exist in database", login);
            req.setAttribute(MESSAGE_BAD_REQUEST, "You do not registered");
            req.setAttribute(REPEAT_LOGIN, login);
            req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
        } else if (userDto.isBlocked()) {
            log.warn("User with id {} is block, and try login", userDto.getId());
            req.setAttribute(MESSAGE_BAD_REQUEST, "You have been blocked");
            req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
        } else if (isCorrectPassword) {
            log.info("User with id {} has come", userDto.getId());
            session.setAttribute(USER_ID, id);// get id
            session.setAttribute(NAME, userDto.getName());
            session.setAttribute(ROLE, userDto.getRole());
            filterChain.doFilter(req, resp);
        } else {
            log.warn("User with id {} has input wrong password", userDto.getId());
            req.setAttribute(REPEAT_LOGIN, login);
            req.setAttribute(MESSAGE_BAD_REQUEST, "Something wrong(");
            req.getRequestDispatcher(LOGIN_FORM).forward(req, resp);
        }
    }
}
