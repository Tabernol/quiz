package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
import models.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AnswerRepo;
import repo.QuestionRepo;
import servises.AnswerService;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * EditQuestion class is res
 */

public class EditQuestion implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditQuestion.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));
        AnswerService answerService = new AnswerService(new AnswerRepo(), new ValidatorService(new DataValidator()));
        String questionId = req.getParameter("question_id");

        req.setAttribute("test_id", req.getParameter("test_id"));
        req.setAttribute("question_id", req.getParameter("question_id"));
        req.setAttribute("page", req.getParameter("page"));

        Question question = null;
        List<Answer> answers = null;
        try {
            question = questionService.get(Long.valueOf(questionId));
            answers = answerService.getAnswers(Long.valueOf(questionId));
            req.setAttribute("answers", answers);
            req.setAttribute("question", question);

            logger.info("Question with ID " + questionId + "has updated");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Question with ID " + questionId + " has NOT updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
