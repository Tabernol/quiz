package controllers.servlet.impl;

import command.post.CreateTest;
import command.EditUser;
import command.post.DeleteTest;
import command.post.DeleteUser;
import command.post.EditTestPost;
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

            System.out.println(req.getParameter("name"));
            System.out.println(req.getParameter("login"));
            String password = req.getParameter("password");
            userService.createUser(name,login,password);
            req.setAttribute("message", "You have registred");


        }));
        COMMANDS.put("/create_test", new CreateTest());
        COMMANDS.put("/delete_test", new DeleteTest());
        COMMANDS.put("/edit_test", new EditTestPost());

    }
}
