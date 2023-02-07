package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
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

public class DeleteAnswer implements RequestHandler {
    private static Logger logger = LogManager.getLogger(DeleteAnswer.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));
        AnswerService answerService = new AnswerService(new AnswerRepo(), new ValidatorService(new DataValidator()));
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        Long answerId = Long.valueOf(req.getParameter("answer_id"));
        String page = req.getParameter("page");

        try {
            answerService.deleteAnswer(answerId);
            req.setAttribute("answers", answerService.getAnswers(questionId));
            req.setAttribute("test_id", testId);
            req.setAttribute("question_id", questionId);
            req.setAttribute("question", questionService.get(questionId));
            req.setAttribute("page", req.getParameter("page"));
            logger.info("Answer with id " + answerId + " has deleted");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);

        } catch (DataBaseException e) {
            logger.warn("Answer with id " + answerId + " has not delete", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
