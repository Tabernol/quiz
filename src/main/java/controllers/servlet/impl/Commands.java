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
        GET_COMMANDS.put(PathConst.REGISTRATION, new Registration());
        GET_COMMANDS.put(PathConst.LOGIN_FORM, new LoginForm());
        GET_COMMANDS.put(PathConst.HOME, new Home());
        GET_COMMANDS.put(PathConst.LOGOUT, new Logout());
        GET_COMMANDS.put(PathConst.LANGUAGE, new LanguageChange());
        //admin??
        GET_COMMANDS.put(PathConst.FILTER_TESTS, new FilterTests());
        GET_COMMANDS.put(PathConst.EDIT_TEST, new EditTest());
        GET_COMMANDS.put(PathConst.EDIT_QUESTION, new EditQuestion());
        GET_COMMANDS.put(PathConst.PROFILE, new Profile());
        GET_COMMANDS.put(PathConst.FILTER_USERS, new FilterUsers());
        GET_COMMANDS.put(PathConst.LOGIN, new MainMenu());


        //student??
        GET_COMMANDS.put(PathConst.START_TEST, new StartTest());
        GET_COMMANDS.put(PathConst.PRG, new Prg());
        GET_COMMANDS.put(PathConst.GET_INFO_QUESTION, new GetInfoQuestion());
        GET_COMMANDS.put(PathConst.FINISH_TEST, new FinishTest());
        GET_COMMANDS.put(PathConst.FINISH, new Finish());
        GET_COMMANDS.put(PathConst.FILTER_RESULT, new FilterResult());
        GET_COMMANDS.put(PathConst.DOWNLOAD, new DownLoad());
        GET_COMMANDS.put(PathConst.FILTER_IMAGES, new FilterImages());
    }

    static {
        POST_COMMANDS.put(PathConst.REGISTRATION, new command.post.Registration());
        POST_COMMANDS.put(PathConst.CREATE_TEST, new CreateTest());
        POST_COMMANDS.put(PathConst.DELETE_TEST, new DeleteTest());
        POST_COMMANDS.put(PathConst.EDIT_TEST, new EditTestPost());
        POST_COMMANDS.put(PathConst.ADD_QUESTION, new AddQuestion());
        POST_COMMANDS.put(PathConst.DELETE_QUESTION, new DeleteQuestion());
        POST_COMMANDS.put(PathConst.ADD_ANSWER, new AddAnswer());
        POST_COMMANDS.put(PathConst.EDIT_QUESTION, new EditQuestionPost());
        POST_COMMANDS.put(PathConst.DELETE_ANSWER, new DeleteAnswer());
        POST_COMMANDS.put(PathConst.EDIT_USER, new EditUserPost());
        POST_COMMANDS.put(PathConst.BLOCK, new BlockUnblockUser());
        POST_COMMANDS.put(PathConst.LOAD, new LoadToCloud());
        POST_COMMANDS.put(PathConst.DELETE_IMAGE, new DeleteFromCloud());
        POST_COMMANDS.put(PathConst.UPDATE_IMAGE, new UpdateImageForQuestion());
        POST_COMMANDS.put(PathConst.BLOCK_TEST, new BlockUnblockTest());
        POST_COMMANDS.put(PathConst.LOGIN, new MainMenu());
    }
}
