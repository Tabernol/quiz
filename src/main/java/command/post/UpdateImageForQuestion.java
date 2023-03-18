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
 * UpdateImageForQuestion.class is allowed only for admin.
 * The meaning of the class is to update url of image of Question to an existing Test(quiz) in database.
 * @author makskrasnopolskyi@gmail.com
 */
public class UpdateImageForQuestion implements RequestHandler {
    private static Logger logger = LogManager.getLogger(UpdateImageForQuestion.class);
    private QuestionService questionService;

    /**
     * Updates the image for a question identified by its ID and redirects the user to the page to edit the question.
     *
     * @param req  the HttpServletRequest object containing the parameters required to update the question's image
     * @param resp the HttpServletResponse object used to send the response back to the client
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException      if an I/O error occurs
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String url = req.getParameter("url");
        String page = req.getParameter("page");

        try {
            questionService = new QuestionService(new QuestionRepoImpl(), new ValidatorService(new DataValidator()));
            questionService.updateImage(url, questionId);
            logger.info("Question with id " + questionId + " has updated image ");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_success=The question has updated");

        } catch (DataBaseException e) {
            logger.warn("Question with id " + questionId + " has not updated image", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Question with id " + questionId + " is invalid ");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page);
        }
    }
}
