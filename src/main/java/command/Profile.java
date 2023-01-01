package command;

import controllers.servlet.RequestHandler;
import dto.ResultDto;
import models.User;
import servises.ResultService;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class Profile implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("user_id");

        UserService userService = new UserService();
        User user = userService.get(userId);
        req.setAttribute("name", user.getName());
        req.setAttribute("login", user.getLogin());
        req.setAttribute("password", user.getPassword());

        ResultService resultService = new ResultService();
        List<ResultDto> resultByUser = resultService.getResultByUser(user.getId());
        req.setAttribute("user_result", resultByUser);


        req.getRequestDispatcher("/WEB-INF/view/student/profile.jsp").forward(req, resp);

    }
}
