package controllers.servlet;

import controllers.servlet.impl.ContentSupplierCommands;
import controllers.servlet.impl.DataHandleCommand;
import dao.connection.MyDataSource;
import models.User;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
//rename edit_user, edit_test because use one url post and get methods
@WebServlet(urlPatterns = {"/tests", "/users", "/registration", "/login_form", "/home",
        "/logout", "/profile", "/testsAZ", "/create_test", "/edit_user",
        "/delete_user", "/filter_tests", "/delete_test",
        "/edit_test", "/add_question", "/delete_question", "/edit_question",
        "/add_answer", "/delete_answer", "/next_page"
})
public class AppControllerServlet extends HttpServlet {

    private static final Logger log = Logger.getLogger(AppControllerServlet.class.getName());
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        MyDataSource.init();
        String lang = req.getParameter("login");
       log.log(Level.WARNING," ddfsgsdfgsdfgsdfg1212121212");

        String servletPath = req.getServletPath();
      //  System.out.println(servletPath);
        ContentSupplierCommands.COMMANDS.get(servletPath).execute(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp)
            throws ServletException, IOException {
        String servletPath = req.getServletPath();
        DataHandleCommand.COMMANDS.get(servletPath).execute(req, resp);
    }


}
