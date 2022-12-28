package command.post;

import controllers.servlet.RequestHandler;
import servises.UserService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Registration implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        UserService userService = new UserService();
        String name = req.getParameter("name");
        String login = req.getParameter("login");
        String password = req.getParameter("password");

        DataValidator dataValidator = new DataValidator();
       if(dataValidator.validatePassword(password)) {
           userService.createUser(name,login,password);
           HttpSession session = req.getSession();
           Long userId = userService.getId(login);
           session.setAttribute("user_id", userId);// get id
           session.setAttribute("name", userService.get(userId).getName());
           session.setAttribute("role", userService.get(userId).getRole());
           req.getRequestDispatcher("/WEB-INF/view/student/student_menu.jsp").forward(req, resp);
       }
       else {
           req.setAttribute("message", "password does not valid");
           req.getRequestDispatcher("/WEB-INF/view/registration.jsp").forward(req, resp);
       }




    }
}
