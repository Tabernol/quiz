package command.post;

import command.get.EditQuestion;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
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

public class EditQuestionPost implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditQuestionPost.class);
    QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService());
    AnswerService answerService = new AnswerService(new AnswerRepo(), new ValidatorService());

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws
            ServletException, IOException {

        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String text = req.getParameter("text");
        String page = req.getParameter("page");

        try {
            questionService.update(text, questionId);
            logger.info("Question with id " + questionId + "has updated");
            resp.sendRedirect(req.getContextPath() + "/prg_edit_question_servlet" +
                    "?test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message=All Right");

        } catch (DataBaseException e) {
            logger.warn("Question with id " + questionId + "has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Question with id " + questionId + "is invalid");

            resp.sendRedirect(req.getContextPath() + "/prg_edit_question_servlet" +
                    "?test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message=" + "text of question is too long" +
                    "&tooLongAnswer=" + text);
        }
    }
}
