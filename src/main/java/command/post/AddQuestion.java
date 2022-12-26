package command.post;

import command.AllTests;
import command.EditTest;
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

public class AddQuestion implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String text = req.getParameter("text");

        System.out.println(text);
        System.out.println(req.getParameter("test_id"));

        QuestionService questionService = new QuestionService();
        questionService.addQuestion(testId, text);

//        TestService testService = new TestService();
//        Test test = testService.get(test_id);
//        req.setAttribute("test_id", test.getId());
//        req.setAttribute("name", test.getName());
//        req.setAttribute("subject", test.getSubject());
//        req.setAttribute("difficult", test.getDifficult());
//        req.setAttribute("duration", test.getDuration());
//
//        List<Question> all = questionService.getAllById(test_id);
//        req.setAttribute("questions", all);
//
//        req.getRequestDispatcher("/WEB-INF/view/admin/edit_test.jsp").forward(req, resp);

        req.setAttribute("test_id", testId);
//
        EditTest editTest = new EditTest();
        editTest.execute(req, resp);


    }
}
