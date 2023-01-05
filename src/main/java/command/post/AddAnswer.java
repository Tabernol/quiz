package command.post;

import command.EditQuestion;
import controllers.servlet.RequestHandler;
import models.Answer;
import servises.AnswerService;
import servises.QuestionService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddAnswer implements RequestHandler {
    AnswerService answerService = new AnswerService();
    QuestionService questionService = new QuestionService();
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {

        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String text = req.getParameter("text");


        if(!DataValidator.validateForNotLongString(text)){
            req.setAttribute("message_answer", "answer is too long");
            req.setAttribute("too_long_answer", text);
            goTo(req,resp,testId,questionId);
        } else {
            req.setAttribute("message_answer", "All Right))");
            Boolean result = Boolean.valueOf(req.getParameter("result"));
            answerService.createAnswer(questionId, text, result);
            goTo(req,resp,testId,questionId);
        }

    }

    private void goTo(HttpServletRequest req, HttpServletResponse resp, Long testId, Long questionId)
            throws ServletException, IOException {
        req.setAttribute("test_id", testId);
        req.setAttribute("question_id", questionId);
        req.setAttribute("page", req.getParameter("page"));

        EditQuestion editQuestion = new EditQuestion();
        editQuestion.execute(req, resp);
    }
}
