package command;

import controllers.servlet.RequestHandler;
import models.Answer;
import models.Question;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditQuestion implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        String testId = req.getParameter("test_id");
        String questionId = req.getParameter("question_id");

        Question question = questionService.get(Long.valueOf(questionId));

        req.setAttribute("question", question);

        req.setAttribute("test_id", req.getParameter("test_id"));


        List<Answer> answers = answerService.getAnswers(Long.valueOf(questionId));
        req.setAttribute("answers", answers);

        req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);

    }
}
