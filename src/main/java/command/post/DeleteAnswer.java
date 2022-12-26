package command.post;

import controllers.servlet.RequestHandler;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAnswer implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService();
        AnswerService answerService = new AnswerService();
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        Long answerId = Long.valueOf(req.getParameter("answer_id"));

        answerService.deleteAnswer(answerId);

        req.setAttribute("answers", answerService.getAnswers(questionId));
        req.setAttribute("test_id", testId);
        req.setAttribute("question_id", questionId);
        req.setAttribute("question", questionService.get(questionId));

        req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);




    }
}
