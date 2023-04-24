package command.post;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.QuestionRepoImpl;
import servises.QuestionService;
import servises.impl.QuestionServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * UpdateImageForQuestion.class is allowed only for admin.
 * The meaning of the class is to update url of image of Question to an existing Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class UpdateImageForQuestion extends AbstractCommand {

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
        QuestionService questionService = AppContext.getInstance().getQuestionService();
        Long testId = Long.valueOf(req.getParameter(TEST_ID));
        Long questionId = Long.valueOf(req.getParameter(QUESTION_ID));
        String url = req.getParameter(URL);
        String page = req.getParameter(PAGE);


        try {
            questionService.updateImage(url, questionId);
            log.info("Question with id {} has updated image ", questionId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_success=The question has updated");

        } catch (DataBaseException e) {
            log.warn("Question with id {} has not updated image", questionId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (ValidateException e) {
            log.info("Question with id {} is invalid ", questionId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page);
        }
    }
}
