package command.post;

import controllers.servlet.RequestHandler;
import models.Answer;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AddAnswer implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        AnswerService answerService = new AnswerService();
        QuestionService questionService = new QuestionService();
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));

        String text = req.getParameter("text");
        Boolean result = Boolean.valueOf(req.getParameter("result"));

        answerService.createAnswer(questionId, text, result);

        List<Answer> answers = answerService.getAnswers(questionId);
        req.setAttribute("answers", answers);

        req.setAttribute("test_id", testId);
        req.setAttribute("question_id", questionId);
        req.setAttribute("question", questionService.get(questionId));
        req.setAttribute("page", req.getParameter("page"));

        req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);
    }
}
