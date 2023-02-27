package controllers.servlet.impl;

import command.get.*;
import command.get.Registration;
import command.post.*;
import constans.PathConst;
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
        //admin??
        GET_COMMANDS.put("/filter_tests", new FilterTests());
        GET_COMMANDS.put("/edit_test", new EditTest());
        GET_COMMANDS.put("/edit_question", new EditQuestion());
        GET_COMMANDS.put("/profile", new Profile());
        GET_COMMANDS.put("/filter_users", new FilterUsers());
        GET_COMMANDS.put(PathConst.LOGIN, new MainMenu());


        //student??
        GET_COMMANDS.put("/start_test", new StartTest());
        GET_COMMANDS.put("/language", new LanguageChange());
        GET_COMMANDS.put("/prg", new Prg());
        GET_COMMANDS.put("/get_text_question", new GetInfoQuestion());
        GET_COMMANDS.put("/finish_test", new FinishTest());
        GET_COMMANDS.put("/finish", new Finish());
        GET_COMMANDS.put("/filter_result", new FilterResult());
        GET_COMMANDS.put("/download", new DownLoad());
        //dev
        GET_COMMANDS.put("/filter_images", new FilterImages());
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
        POST_COMMANDS.put("/load", new LoadToCloud());
        POST_COMMANDS.put("/delete_image", new DeleteFromCloud());
        POST_COMMANDS.put("/update_image", new UpdateImageForQuestion());
        POST_COMMANDS.put("/remove_image", new RemoveImageForQuestion());
        POST_COMMANDS.put("/block_test", new BlockUnblockTest());
        POST_COMMANDS.put(PathConst.LOGIN, new MainMenu());
    }
}
