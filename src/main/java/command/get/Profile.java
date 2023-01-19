package command.get;

import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.User;
import repo.ResultRepo;
import repo.UserRepo;
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

        UserService userService = new UserService(new UserRepo());
        ResultService resultService = new ResultService(new ResultRepo());

        User user = null;
        List<ResultDto> resultByUser = null;
        try {
            user = userService.get(userId);
            resultByUser = resultService.getResultByUser(user.getId());
            req.setAttribute("name", user.getName());
            req.setAttribute("user_result", resultByUser);

            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("/WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
