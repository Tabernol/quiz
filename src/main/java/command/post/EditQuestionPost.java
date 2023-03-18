package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.impl.QuestionRepoImpl;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * EditQuestionPost.class is allowed only for admin.
 * The meaning of the class is to update a Question to an existing Test(quiz) in database.
 * @author makskrasnopolskyi@gmail.com
 */
public class EditQuestionPost implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditQuestionPost.class);
    QuestionService questionService = new QuestionService(new QuestionRepoImpl(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to update this Question
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
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
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_success=The question updated");

        } catch (DataBaseException e) {
            logger.warn("Question with id " + questionId + "has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Question with id " + questionId + "is invalid");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_bad_request=" + "text of question is too long" +
                    "&tooLongAnswer=" + text);
        }
    }
}
