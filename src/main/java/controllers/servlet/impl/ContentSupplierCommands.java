package controllers.servlet.impl;

import command.*;
import command.post.DeleteTest;
import command.post.DeleteUser;
import controllers.servlet.RequestHandler;
import models.Test;
import models.User;
import servises.TestService;
import servises.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ContentSupplierCommands {
    public static final Map<String, RequestHandler> COMMANDS = new HashMap<>();


    static {
        COMMANDS.put("/users", new AllUser());
        COMMANDS.put("/tests", new AllTests());
        COMMANDS.put("/registration", ((req, resp) -> {
            req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
        }));
        COMMANDS.put("/login_form", ((req, resp) -> {
            String lang = req.getParameter("lang");
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }));
        COMMANDS.put("/home", new Home());
        COMMANDS.put("/logout", new Logout());
        COMMANDS.put("/profile", new Profile());
        COMMANDS.put("/edit_user", new EditUser());
        COMMANDS.put("/delete_user", new DeleteUser());
        COMMANDS.put("/filter_tests", new FilterTests());
        COMMANDS.put("/edit_test", new EditTest());
        COMMANDS.put("/edit_question", new EditQuestion());
        COMMANDS.put("/profile", new Profile());
        COMMANDS.put("/next_page", new NextPage());

    }


}
