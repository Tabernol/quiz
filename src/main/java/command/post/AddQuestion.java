package command.post;

import command.EditTest;
import controllers.servlet.RequestHandler;
import models.Question;
import models.Test;
import servises.QuestionService;
import servises.TestService;
import validator.DataValidator;

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
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("test_id", testId);

        String text = req.getParameter("text");

        if(!DataValidator.validateForNotLongString(text)){
            req.setAttribute("message_question", "text of question is too long");
            req.setAttribute("too_Long_Text", text);
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        } else {
            QuestionService questionService = new QuestionService();
            questionService.addQuestion(testId, text);
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        }







    }
}
