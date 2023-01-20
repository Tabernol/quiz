package command.get;

import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Test;
import models.User;
import repo.ResultRepo;
import repo.TestRepo;
import repo.UserRepo;
import servises.ResultService;
import servises.TestService;
import servises.UserService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class Profile implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {

        Long userId = (Long) req.getSession().getAttribute("user_id");
        String sub = req.getParameter("sub");
        String order = req.getParameter("order");
        String rows = req.getParameter("rows");

        UserService userService = new UserService(new UserRepo(), new ValidatorService());
        ResultService resultService = new ResultService(new ResultRepo());

        //new
        TestService testService =new TestService(new TestRepo(), new ValidatorService());

        int countPages;
        List<String> subjects;
        List<Test> filterTests;
        try {
            countPages = testService.countPages(sub, Integer.valueOf(rows));
            filterTests = testService.getFilterTests(sub, order, Integer.valueOf(rows));
            subjects = testService.getDistinctSubjects();
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }





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
