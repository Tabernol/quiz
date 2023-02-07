package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Question;
import models.Test;
import repo.QuestionRepo;
import repo.TestRepo;
import servises.QuestionService;
import servises.TestService;
import servises.ValidatorService;
import validator.DataValidator;

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
        Long id = Long.valueOf(req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));

        TestService testService = new TestService(new TestRepo(), new ValidatorService(new DataValidator()));
        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));
        List<Question> all;

        try {
            Test test = testService.get(id);
            all = questionService.getAllById(id);

            req.setAttribute("test_id", test.getId());
            req.setAttribute("name", test.getName());
            req.setAttribute("subject", test.getSubject());
            req.setAttribute("difficult", test.getDifficult());
            req.setAttribute("duration", test.getDuration());
            req.setAttribute("questions", all);
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_test.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }

    }
}
