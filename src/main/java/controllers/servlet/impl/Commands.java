package controllers.servlet.impl;

import command.get.*;
import command.get.Registration;
import command.post.*;
import controllers.servlet.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class Commands {
    public static final Map<String, RequestHandler> GET_COMMANDS = new HashMap<>();
    public static final Map<String, RequestHandler> POST_COMMANDS = new HashMap<>();

    static {
        //guest
        GET_COMMANDS.put("/registration", new Registration());
        GET_COMMANDS.put("/login_form", new LoginForm());
        GET_COMMANDS.put("/home", new Home());
        GET_COMMANDS.put("/logout", new Logout());
        //admin
        GET_COMMANDS.put("/filter_tests", new FilterTests());
        GET_COMMANDS.put("/edit_test", new EditTest());
        GET_COMMANDS.put("/edit_question", new EditQuestion());
        GET_COMMANDS.put("/profile", new Profile());
        GET_COMMANDS.put("/start_test", new StartTest());
        //student
        GET_COMMANDS.put("/language", new LanguageChange());
        GET_COMMANDS.put("/prg", new Prg());
        GET_COMMANDS.put("/get_text_question", new GetInfoQuestion());
        GET_COMMANDS.put("/finish_test", new FinishTest());
        GET_COMMANDS.put("/finish", new Finish());
        GET_COMMANDS.put("/filter_result", new FilterResult());
        GET_COMMANDS.put("/download", new DownLoad());
        GET_COMMANDS.put("/filter_users", new FilterUsers());
    }

    static {
        POST_COMMANDS.put("/registration", new command.post.Registration());
        POST_COMMANDS.put("/create_test", new CreateTest());
        POST_COMMANDS.put("/delete_test", new DeleteTest());
        POST_COMMANDS.put("/edit_test", new EditTestPost());
        POST_COMMANDS.put("/add_question", new AddQuestion());
        POST_COMMANDS.put("/delete_question", new DeleteQuestion());
        POST_COMMANDS.put("/add_answer", new AddAnswer());
        POST_COMMANDS.put("/edit_question", new EditQuestionPost());
        POST_COMMANDS.put("/delete_answer", new DeleteAnswer());
        POST_COMMANDS.put("/edit_user", new EditUserPost());
        POST_COMMANDS.put("/block", new BlockUnblockUser());
        POST_COMMANDS.put("/upload_image", new UpLoadImage());
    }
}
