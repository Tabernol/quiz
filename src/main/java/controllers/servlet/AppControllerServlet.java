package controllers.servlet;


import controllers.PathConst;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;


@WebServlet(urlPatterns = {
        PathConst.REGISTRATION, PathConst.LOGIN_FORM, PathConst.LOGOUT,
        PathConst.LANGUAGE, PathConst.LOGIN,

        PathConst.HOME, PathConst.PROFILE, PathConst.BLOCK, PathConst.EDIT_USER,

        PathConst.FILTER_USERS, PathConst.FILTER_TESTS, PathConst.FILTER_RESULT,PathConst.FILTER_IMAGES,

        PathConst.CREATE_TEST, PathConst.EDIT_TEST, PathConst.DELETE_TEST, PathConst.BLOCK_TEST,

        PathConst.ADD_QUESTION, PathConst.EDIT_QUESTION, PathConst.DELETE_QUESTION,
        PathConst.UPDATE_IMAGE, PathConst.REMOVE_IMAGE,

        PathConst.ADD_ANSWER, PathConst.DELETE_ANSWER,

        PathConst.LOAD, PathConst.DELETE_IMAGE,

        PathConst.START_TEST, PathConst.RESULT_ANSWER, PathConst.DOWNLOAD,
        PathConst.GET_INFO_QUESTION, PathConst.FINISH_TEST, PathConst.FINISH,

        PathConst.PRG
}
)
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
@Slf4j
public class AppControllerServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        Commands.GET_COMMANDS.get(servletPath).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        Commands.POST_COMMANDS.get(servletPath).execute(req, resp);
    }

    private static final String REGISTRATION = "/registration";
    private static final String LOGIN_FORM = "/login_form";
    private static final String LANGUAGE = "/language";
    // for both
    private static final String LOGIN = "/login";
    private static final String LOGOUT = "/logout";
    private static final String PROFILE = "/profile";
    private static final String FILTER_TESTS = "/filter_tests";
    private static final String FILTER_RESULT = "/filter_result";
    private static final String DOWNLOAD = "/download";
    private static final String HOME = "/home";
    private static final String PRG = "/prg";

    //only student
    private static final String INFO_TEST = "/info_test";
    private static final String START_TEST = "/start_test";
    private static final String RESULT_ANSWER = "/result_answer";
    private static final String GET_INFO_QUESTION = "/get_text_question";
    private static final String FINISH = "/finish";
    private static final String FINISH_TEST = "/finish_test";
    //only admin
    private static final String FILTER_USERS = "/filter_users";
    private static final String BLOCK = "/block";
    private static final String CREATE_TEST = "/create_test";
    private static final String ADD_QUESTION = "/add_question";
    private static final String ADD_ANSWER = "/add_answer";
    private static final String EDIT_USER = "/edit_user";
    private static final String EDIT_TEST = "/edit_test";
    private static final String EDIT_QUESTION = "/edit_question";
    private static final String DELETE_USER = "/delete_user";
    private static final String DELETE_TEST = "/delete_test";
    private static final String DELETE_QUESTION = "/delete_question";
    private static final String DELETE_ANSWER = "/delete_answer";
    private static final String FILTER_IMAGES = "/filter_images";
    private static final String LOAD = "/load";
    private static final String DELETE_IMAGE = "/delete_image";
    private static final String UPDATE_IMAGE = "/update_image";
    private static final String REMOVE_IMAGE = "/remove_image";
    private static final String BLOCK_TEST = "/block_test";
}
