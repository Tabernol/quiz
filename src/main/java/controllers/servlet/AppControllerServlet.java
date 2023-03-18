package controllers.servlet;


import constans.PathConst;
import controllers.servlet.impl.Commands;



import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


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
})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1, // 1 MB
        maxFileSize = 1024 * 1024 * 10,      // 10 MB
        maxRequestSize = 1024 * 1024 * 100   // 100 MB
)
public class AppControllerServlet extends HttpServlet {

    static final Logger log = LogManager.getLogger(AppControllerServlet.class);

    static {
        log.info("start APP");
    }

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


}
