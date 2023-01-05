package command;

import controllers.servlet.RequestHandler;
import models.User;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllUser implements RequestHandler {
    UserService userService = new UserService();
    @Override
    public void execute(HttpServletRequest req, 
                        HttpServletResponse resp) 
            throws ServletException, IOException {
        List<User> all = userService.getAll();
        req.setAttribute("users",all );
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_users.jsp").forward(req,resp);

        String pathInfo = req.getPathInfo();
        String contextPath = req.getContextPath();
        String requestURI = req.getRequestURI();
        System.out.println("context =  "+contextPath);
        System.out.println("path info "+ pathInfo);
        System.out.println("uri  = "+requestURI);



    }
}
