package command.post;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
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
 * CreateTest.class is allowed only for admin.
 * The meaning of the class is to create Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class CreateTest implements RequestHandler {

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
        TestService testService = AppContext.getInstance().getTestService();
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        try {
            testService.createTest(new TestDto(name, subject, difficult, duration));
            log.info("Test " + name + "has created");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&sub=all&order=name+asc&rows=5" +
                    "&message_success=The test created");
        } catch (ValidateException e) {
            log.warn("Test " + name + " is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&sub=all&order=name+asc&rows=5" +
                    "&name=" + name +
                    "&subject=" + subject +
                    "&difficult=" + difficult +
                    "&duration=" + duration +
                    "&message_bad_request=" + e.getMessage());
        } catch (DataBaseException e) {
            log.warn("Test " + name + "have not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
