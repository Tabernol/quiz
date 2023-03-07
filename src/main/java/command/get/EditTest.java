package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Question;
import models.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.QuestionRepo;
import repo.TestRepo;
import servises.QuestionService;
import servises.TestService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * EditTest.class is allowed only for admin.
 * The purpose of this class is to redirect to page for editing test(quiz).
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class EditTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditTest.class);
    private TestService testService;
    private QuestionService questionService;

    /**
     * This method reads parameters.
     * He contacts the service level to get information about his test(quiz) and questions for it.
     * Set this information in request and calls it.
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
        testService = new TestService(new TestRepo(), new ValidatorService(new DataValidator()));
        questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));
        Long id = Long.valueOf(req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));

        List<Question> all;

        try {
            Test test = testService.get(id);
            all = questionService.getAllById(id);

            req.setAttribute("test_id", test.getId());
            req.setAttribute("name", test.getName());
            req.setAttribute("subject", test.getSubject());
            req.setAttribute("difficult", test.getDifficult());
            req.setAttribute("duration", test.getDuration());
            req.setAttribute("questions", all);
            logger.info("Test with ID " + id + " has updated");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_test.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Test with ID " + id + " has NOT updated ", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);

        }
    }
}
