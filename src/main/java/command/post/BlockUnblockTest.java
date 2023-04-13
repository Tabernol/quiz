package command.post;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.TestRepoImpl;
import servises.TestService;
import servises.impl.TestServiceImpl;
import servises.impl.ValidatorServiceImpl;
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
@Slf4j
public class BlockUnblockTest implements RequestHandler {

    /**
     * This method is read parameter from request.
     * It calls the service layer to change status Test (block/unblock)
     * if DataBaseException is caught, redirects to error page.
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        TestService testService = AppContext.getInstance().getTestService();

        Long testId = Long.valueOf(req.getParameter("test_id"));
        String page = req.getParameter("page");

        try {
            testService.changeStatus(testId);
            log.info("Test with id " + testId + " changed status.");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&page=" + page);
        } catch (DataBaseException e) {
            log.warn("Test with id " + testId + " has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
