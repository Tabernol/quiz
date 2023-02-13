package controllers.servlet;


import constans.PathConst;
import controllers.servlet.impl.ContentSupplierCommands;
import controllers.servlet.impl.DataHandleCommand;
import connection.MyDataSource;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

//rename edit_user, edit_test because use one url post and get methods
@WebServlet(urlPatterns = {
        PathConst.REGISTRATION, PathConst.LOGIN_FORM, PathConst.LOGOUT,
        PathConst.HOME, PathConst.PROFILE,
        PathConst.USERS,
        PathConst.FILTER_TESTS,
        PathConst.CREATE_TEST,
        PathConst.EDIT_USER, PathConst.EDIT_TEST, PathConst.EDIT_QUESTION,
        PathConst.DELETE_USER, PathConst.DELETE_TEST, PathConst.DELETE_QUESTION, PathConst.DELETE_ANSWER,
        PathConst.ADD_QUESTION, PathConst.ADD_ANSWER,
        PathConst.INFO_TEST, PathConst.START_TEST,
        PathConst.NEXT_QUESTION, PathConst.RESULT_ANSWER,
        PathConst.LANGUAGE,
        "/prg",
        "/to_create_test", "/edit_profile"
        ,"/block", "/get_text_question", "/finish_test", "/finish",
        "/filter_result", "/download", "/filter_users"

})
public class AppControllerServlet extends HttpServlet {

    static final Logger log = LogManager.getLogger(AppControllerServlet.class);

    static {
        log.info("start APP");
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter("login");
        log.info("get");
        String servletPath = req.getServletPath();
        ContentSupplierCommands.COMMANDS.get(servletPath).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        log.info("post");
        String servletPath = req.getServletPath();
        DataHandleCommand.COMMANDS.get(servletPath).execute(req, resp);
    }


}
