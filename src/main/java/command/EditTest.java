package command;

import controllers.servlet.RequestHandler;
import models.Question;
import models.Test;
import servises.QuestionService;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("id"));
        TestService testService = new TestService();
        QuestionService questionService = new QuestionService();
        Test test = testService.get(id);
        req.setAttribute("name", test.getName());
        req.setAttribute("subject", test.getSubject());
        req.setAttribute("difficult", test.getDifficult());
        req.setAttribute("duration", test.getDuration());

        List<Question> all = questionService.getAllById(id);
        req.setAttribute("questions", all);


        req.getRequestDispatcher("/WEB-INF/view/admin/edit_test.jsp").forward(req, resp);
    }
}
