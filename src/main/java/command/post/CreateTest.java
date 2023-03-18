package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
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
 * CreateTest.class is allowed only for admin.
 * The meaning of the class is to create Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class CreateTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(CreateTest.class);
    TestService testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));

    /**
     * This method is read parameter from request.
     * It calls the service layer to create Test(quiz)
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        try {
            int result = testService.createTest(name, subject, difficult, duration);
            logger.info("Test " + name + "has created");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&sub=all&order=name+asc&rows=5" +
                    "&message_success=The test created");
        } catch (ValidateException e) {
            logger.warn("Test " + name + " is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&sub=all&order=name+asc&rows=5" +
                    "&name=" + name +
                    "&subject=" + subject +
                    "&difficult=" + difficult +
                    "&duration=" + duration +
                    "&message_bad_request=" + e.getMessage());
        } catch (DataBaseException e) {
            logger.warn("Test " + name + "have not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
