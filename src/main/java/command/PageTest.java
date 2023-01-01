package command;

import controllers.servlet.RequestHandler;
import models.Question;
import models.Test;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class PageTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        TestService testService = new TestService();
        req.setAttribute("page", req.getParameter("page"));
        Long testId = Long.valueOf(req.getParameter("test_id"));


        Test test = testService.get(testId);
        req.setAttribute("test_id", test.getId());
        req.setAttribute("name", test.getName());
        req.setAttribute("subject", test.getSubject());
        req.setAttribute("difficult", test.getDifficult());
        req.setAttribute("duration", test.getDuration());


        req.getRequestDispatcher("/WEB-INF/view/student/page_test.jsp").forward(req, resp);



    }
}
