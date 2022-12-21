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

@WebServlet(urlPatterns = {"/tests", "/users", "/registration", "/login_form", "/home", "/logout", "/profile"})
public class AppControllerServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp)
            throws ServletException, IOException {
        MyDataSource.init();
        String lang = req.getParameter("login");
        System.out.println("lang =   "+ lang);




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
