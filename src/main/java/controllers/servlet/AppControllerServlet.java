package controllers.servlet;


import constans.PathConst;
import controllers.servlet.impl.ContentSupplierCommands;
import controllers.servlet.impl.DataHandleCommand;
import dao.connection.MyDataSource;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

//rename edit_user, edit_test because use one url post and get methods
@WebServlet(urlPatterns = {
        PathConst.REGISTRATION, PathConst.LOGIN_FORM, PathConst.LOGOUT,
        PathConst.HOME, PathConst.PROFILE,
        PathConst.USERS,
        PathConst.FILTER_TESTS, PathConst.NEXT_PAGE,
        PathConst.CREATE_TEST,
        PathConst.EDIT_USER, PathConst.EDIT_TEST, PathConst.EDIT_QUESTION,
        PathConst.DELETE_USER, PathConst.DELETE_TEST, PathConst.DELETE_QUESTION, PathConst.DELETE_ANSWER,
        PathConst.ADD_QUESTION, PathConst.ADD_ANSWER,
        PathConst.INFO_TEST, PathConst.START_TEST,
        "/next_question",
        "/result_answer"

})
public class AppControllerServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AppControllerServlet.class.getName());

    static {
        log.log(Level.WARNING, "START");
        MyDataSource.init();
    }

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter("login");
        log.log(Level.WARNING, " GET");
        String servletPath = req.getServletPath();
        ContentSupplierCommands.COMMANDS.get(servletPath).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        log.log(Level.WARNING, " POST");
        String servletPath = req.getServletPath();
        DataHandleCommand.COMMANDS.get(servletPath).execute(req, resp);
    }


}
