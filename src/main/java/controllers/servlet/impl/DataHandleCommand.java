package controllers.servlet.impl;

import controllers.servlet.RequestHandler;
import servises.UserService;

import java.util.HashMap;
import java.util.Map;

public class DataHandleCommand {

    public static final Map<String, RequestHandler> COMMANDS = new HashMap<>();
    static {
        COMMANDS.put("/registration", ((req, resp) -> {
            UserService userService = new UserService();
            String name = req.getParameter("name");
            String login = req.getParameter("login");
            String password = req.getParameter("password");
            userService.createUser(name,login,password);
            req.setAttribute("message", "You have registred");


        }));
    }
}
