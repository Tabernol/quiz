package controllers.servlet.impl;

import command.*;
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
//        COMMANDS.put("/tests", ((req, resp) -> {
//            TestService testService = new TestService();
//            List<Test> all = testService.getAll();
//            req.getSession().setAttribute("tests", all);
//            req.getRequestDispatcher("/").forward(req, resp);
//
//        }));
        COMMANDS.put("/tests", new AllTests());
        COMMANDS.put("/registration", ((req, resp) -> {
            req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
        }));
        COMMANDS.put("/login_form", ((req, resp) -> {
            String lang = req.getParameter("lang");
            System.out.println("lang   =  " + lang);
            req.getRequestDispatcher("/WEB-INF/view/login_form.jsp").forward(req, resp);
        }));
        COMMANDS.put("/home", new Home());
        COMMANDS.put("/logout", new Logout());
        COMMANDS.put("/profile", new Profile());
    }


}
