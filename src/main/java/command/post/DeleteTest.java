package command.post;

import command.AbstractCommand;
import command.get.FilterTests;
import controllers.AppContext;
import controllers.PathConst;
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
 * DeleteTest.class is allowed only for admin.
 * The meaning of the class is to delete Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class DeleteTest extends AbstractCommand {

    /**
     * This method is read parameter from request.
     * It calls the service layer to delete a Test(quiz)
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
        Long id = Long.valueOf(req.getParameter(TEST_ID));
        String page = req.getParameter(PAGE);

        try {
            testService.delete(id);
            log.info("Test with id {} has deleted", id);
            resp.sendRedirect(req.getContextPath() + PathConst.FILTER_TESTS + "?page=" + page);
        } catch (DataBaseException e) {
            log.warn("Test with id {} has not deleted", id, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);

        }
    }
}
