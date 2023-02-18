package controllers.servlet.impl;

import command.get.*;
import command.post.FinishTest;
import controllers.servlet.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class ContentSupplierCommands {
    public static final Map<String, RequestHandler> COMMANDS = new HashMap<>();


    static {
        COMMANDS.put("/registration", new Registration());
        COMMANDS.put("/login_form", new LoginForm());
        COMMANDS.put("/home", new Home());
        COMMANDS.put("/logout", new Logout());

        COMMANDS.put("/filter_tests", new FilterTests());
        COMMANDS.put("/edit_test", new EditTest());
        COMMANDS.put("/edit_question", new EditQuestion());
        COMMANDS.put("/profile", new Profile());
        COMMANDS.put("/start_test", new StartTest());

        COMMANDS.put("/language", new LanguageChange());
        COMMANDS.put("/prg", new Prg());
        COMMANDS.put("/get_text_question", new GetInfoQuestion());
        COMMANDS.put("/finish_test", new FinishTest());
        COMMANDS.put("/finish", new Finish());
        COMMANDS.put("/filter_result", new FilterResult());
        COMMANDS.put("/download", new DownLoad());
        COMMANDS.put("/filter_users", new FilterUsers());

    }


}
