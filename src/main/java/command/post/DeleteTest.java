package command.post;

import command.get.FilterTests;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;

import lombok.extern.slf4j.Slf4j;
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
@Slf4j
public class DeleteTest implements RequestHandler {
    private TestService testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to delete a Test(quiz)
     * if DataBaseException is caught, redirects to error page.
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.valueOf(req.getParameter("test_id"));
        req.setAttribute("page", req.getParameter("page"));
        try {
            testService.delete(id);
            log.info("Test with id " + id + "has deleted");
            FilterTests filterTests = new FilterTests();
            filterTests.execute(req, resp);
        } catch (DataBaseException e) {
            log.warn("Test with id " + id + "has not deleted", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);

        }
    }
}
