package controllers.filters;

import connection.MyDataSource;
import exeptions.DataBaseException;
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

//@WebFilter(filterName = "AuthorizationFilter", value = "/login")
public class AuthorizationFilter extends AbstractFilter {
    private static Logger logger = LogManager.getLogger(AuthorizationFilter.class);

    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        final HttpSession session = req.getSession();//what first??
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");// or this first??
        String lang = req.getParameter("lang");
        UserService userService = new UserService(new UserRepo(), new ValidatorService());
        String role;
        long id;

        try {
            id = userService.getId(login);
            if (id == -1) {
                req.setAttribute("message", "You do not registered");
                req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
            } else if (userService.isBlocked(id)) {
                logger.warn("User with id " + userService.get(id).getId() + "is block, and try login");
                req.setAttribute("message", "You are blocked");
                req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
            } else if (isCorrectPassword(id, password)) {
                logger.warn("User with id " + userService.get(id).getId() + "has come");
                session.setAttribute("user_id", id);// get id
                session.setAttribute("name", userService.get(id).getName());
                session.setAttribute("role", userService.get(id).getRole());
                filterChain.doFilter(req, resp);

            } else {
                logger.warn("User with id " + userService.get(id).getId() + "has input wrong password");
                req.setAttribute("message", "Something wrong(");
                req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
            }

        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }
    }


    private boolean isCorrectPassword(Long userId, String password) {
        UserService userService = new UserService(new UserRepo(), new ValidatorService());
        try {
            String passwordInDataBase = userService.get(userId).getPassword();
            return PasswordHashingService.validatePassword(password, passwordInDataBase);
        } catch (DataBaseException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }
}
