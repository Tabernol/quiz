package command.post;

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
 * BlockUnblockTest.class is allowed only for admin
 * This class responsibility for retrieve status of test to change it
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class BlockUnblockTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(BlockUnblockTest.class);

    private TestService testService;

    /**
     * This method is read parameter from request.
     * It calls the service layer to change status Test (block/unblock)
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
        testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));

        Long testId = Long.valueOf(req.getParameter("test_id"));
        String page = req.getParameter("page");

        try {
            testService.changeStatus(testId);
            logger.info("Test with id " + testId + " changed status.");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&page=" + page);
        } catch (DataBaseException e) {
            logger.warn("Test with id " + testId + " has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
