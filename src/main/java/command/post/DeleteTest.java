package command.post;

import command.get.FilterTests;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.impl.TestRepoImpl;
import servises.TestService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
/**
 * DeleteTest.class is allowed only for admin.
 * The meaning of the class is to delete Test(quiz) in database.
 * @author makskrasnopolskyi@gmail.com
 */
public class DeleteTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(DeleteTest.class);
    TestService testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to delete a Test(quiz)
     * if DataBaseException is caught, redirects to error page.
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));

        try {
            testService.delete(id);
            logger.info("Test with id " + id + "has deleted");
            FilterTests filterTests = new FilterTests();
            filterTests.execute(req, resp);
        } catch (DataBaseException e) {
            logger.warn("Test with id " + id + "has not deleted", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);

        }


    }
}
