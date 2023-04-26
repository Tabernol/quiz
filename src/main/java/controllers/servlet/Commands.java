package controllers.servlet;

import command.get.*;
import command.get.Registration;
import command.post.*;

import java.util.HashMap;
import java.util.Map;

import static controllers.PathConst.*;

public class Commands {
    public static final Map<String, RequestHandler> GET_COMMANDS = new HashMap<>();
    public static final Map<String, RequestHandler> POST_COMMANDS = new HashMap<>();

    static {
        //guest
        GET_COMMANDS.put(REGISTRATION, new Registration());
        GET_COMMANDS.put(LOGIN_FORM, new LoginForm());
        GET_COMMANDS.put(HOME, new Home());
        GET_COMMANDS.put(LOGOUT, new Logout());
        GET_COMMANDS.put(LANGUAGE, new LanguageChange());
        //admin??
        GET_COMMANDS.put(FILTER_TESTS, new FilterTests());
        GET_COMMANDS.put(EDIT_TEST, new EditTest());
        GET_COMMANDS.put(EDIT_QUESTION, new EditQuestion());
        GET_COMMANDS.put(PROFILE, new Profile());
        GET_COMMANDS.put(FILTER_USERS, new FilterUsers());
        GET_COMMANDS.put(LOGIN, new MainMenu());


        //student??
        GET_COMMANDS.put(START_TEST, new StartTest());
        GET_COMMANDS.put(PRG, new Prg());
        GET_COMMANDS.put(GET_INFO_QUESTION, new GetInfoQuestion());
        GET_COMMANDS.put(FINISH_TEST, new FinishTest());
        GET_COMMANDS.put(FINISH, new Finish());
        GET_COMMANDS.put(FILTER_RESULT, new FilterResult());
        GET_COMMANDS.put(DOWNLOAD, new DownLoad());
        GET_COMMANDS.put(FILTER_IMAGES, new FilterImages());
    }

    static {
        POST_COMMANDS.put(REGISTRATION, new command.post.Registration());
        POST_COMMANDS.put(CREATE_TEST, new CreateTest());
        POST_COMMANDS.put(DELETE_TEST, new DeleteTest());
        POST_COMMANDS.put(EDIT_TEST, new EditTestPost());
        POST_COMMANDS.put(ADD_QUESTION, new AddQuestion());
        POST_COMMANDS.put(DELETE_QUESTION, new DeleteQuestion());
        POST_COMMANDS.put(ADD_ANSWER, new AddAnswer());
        POST_COMMANDS.put(EDIT_QUESTION, new EditQuestionPost());
        POST_COMMANDS.put(DELETE_ANSWER, new DeleteAnswer());
        POST_COMMANDS.put(EDIT_USER, new EditUserPost());
        POST_COMMANDS.put(BLOCK, new BlockUnblockUser());
        POST_COMMANDS.put(LOAD, new LoadToCloud());
        POST_COMMANDS.put(DELETE_IMAGE, new DeleteFromCloud());
        POST_COMMANDS.put(UPDATE_IMAGE, new UpdateImageForQuestion());
        POST_COMMANDS.put(BLOCK_TEST, new BlockUnblockTest());
        POST_COMMANDS.put(LOGIN, new MainMenu());
    }

}