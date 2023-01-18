package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.UserRepo;
import servises.PasswordHashingService;
import servises.UserService;
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

        UserService userService = new UserService(new UserRepo());
        try {
            if (userService.getId(login) != -1) {
                req.setAttribute("message", "this login already exists");
                req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
            } else if (!DataValidator.validateLogin(login)) {
                req.setAttribute("message", "login is invalid");
                req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
            } else if (!DataValidator.validatePassword(password)) {
                req.setAttribute("message", "password is invalid");
                req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
            } else if (!DataValidator.validateForName(name)) {
                req.setAttribute("message", "name is invalid");
                req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
            } else {
                String passwordHash;
                try {
                    passwordHash = PasswordHashingService.generateStrongPasswordHash(password);
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                } catch (InvalidKeySpecException e) {
                    throw new RuntimeException(e);
                }
                userService.createUser(name, login, passwordHash);
                Long userId = userService.getId(login);
                HttpSession session = req.getSession();
                session.setAttribute("user_id", userId);// get id
                session.setAttribute("name", userService.get(userId).getName());
                session.setAttribute("role", userService.get(userId).getRole());
                req.getRequestDispatcher("/WEB-INF/view/menu.jsp").forward(req, resp);
            }
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }
    }
}
