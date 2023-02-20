package command.post;

import command.get.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * AddQuestion.class is allowed only for admin.
 * The meaning of the class is to add a Question to an existing Test(quiz) in database.
 * @author makskrasnopolskyi@gmail.com
 */
public class AddQuestion implements RequestHandler {

    private static Logger logger = LogManager.getLogger(AddQuestion.class);
    QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to create Question
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String page = req.getParameter("page");
        String text = req.getParameter("text");

        try {
            questionService.addQuestion(testId, text);
            logger.info("Question for test id " + testId + "has added");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test"+
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_question=All Right)");

        } catch (DataBaseException e) {
            logger.warn("Question for test id " + testId + "has not added", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Question for test id " + testId + "is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test"+
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_question=" + "text of question is too long");
        }
    }
}
