package command.post;

import command.get.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
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
 * DeleteQuestion.class is allowed only for admin.
 * The meaning of the class is to delete a Question in an existing Question in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class DeleteQuestion implements RequestHandler {

    private static Logger logger = LogManager.getLogger(DeleteQuestion.class);

    QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to delete a Question
     * if DataBaseException is caught, redirects to error page.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("test_id", req.getParameter("test_id"));
        String id = req.getParameter("question_id");

        try {
            questionService.deleteQuestion(Long.valueOf(id));
            logger.info("Question with id " + id + "has deleted");
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Question with id " + id + "has not delete", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
