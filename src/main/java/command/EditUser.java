package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.UserRepo;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

public class EditUser implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long userId = Long.valueOf(req.getParameter("user_id"));
        UserService userService = new UserService(new UserRepo());
        try {
            req.setAttribute("user", userService.get(userId));
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_user.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }




    }
}
