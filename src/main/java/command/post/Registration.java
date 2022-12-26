package command.post;

import controllers.servlet.RequestHandler;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Registration implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        userService.createUser(name,login,password);


    }
}
