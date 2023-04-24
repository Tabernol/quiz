package command.post;

import command.AbstractCommand;
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
 * EditTestPost.class is allowed only for admin.
 * The meaning of the class is to update a Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditTestPost extends AbstractCommand {
    /**
     * This method is read parameter from request.
     * It calls the service layer to update this Test(quiz)
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        TestService testService = AppContext.getInstance().getTestService();
        Long testId = Long.valueOf(req.getParameter(TEST_ID));
        String name = req.getParameter(NAME);
        String subject = req.getParameter(SUBJECT);
        int difficult = Integer.parseInt(req.getParameter(DIFFICULT));
        int duration = Integer.parseInt(req.getParameter(DURATION));
        String page = req.getParameter(PAGE);


        try {
            testService.update(TestDto.builder()
                    .id(testId)
                    .name(name)
                    .subject(subject)
                    .difficult(difficult)
                    .duration(duration)
                    .build());
            log.info("Test with id {} has updated", testId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test" +
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_success=The test updated");
        } catch (DataBaseException e) {
            log.warn("Test with id {} has not updated", testId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (ValidateException e) {
            log.info("Test with id {} is invalid, because ", testId, e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test" +
                    "&page=" + page +
                    "&test_id=" + testId +
                    "&message_bad_request=" + e.getMessage());
        }
    }
}
