package command.post;

import controllers.servlet.RequestHandler;
import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
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
 * EditTestPost.class is allowed only for admin.
 * The meaning of the class is to update a Test(quiz) in database.
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditTestPost implements RequestHandler {
    private TestService testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));
    /**
     * This method is read parameter from request.
     * It calls the service layer to update this Test(quiz)
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        String page = req.getParameter("page");


        try {
            testService.update(new TestDto(testId, name, subject, difficult, duration));
            log.info("Test with id " + testId + "has updated");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test" +
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_success=The test updated");
        } catch (DataBaseException e) {
            log.warn("Test with id " + testId + "has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            log.info("Test with id " + testId + "is invalid, because ", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test" +
                    "&page=" + page +
                    "&test_id=" + testId +
                    "&message_bad_request=" + e.getMessage());
        }
    }
}
