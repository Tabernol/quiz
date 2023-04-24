package command.post;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.TestService;

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
public class CreateTest extends AbstractCommand {

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
        String name = req.getParameter(NAME);
        String subject = req.getParameter(SUBJECT);
        int difficult = Integer.parseInt(req.getParameter(DIFFICULT));
        int duration = Integer.parseInt(req.getParameter(DURATION));

        try {
            testService.create(TestDto.builder()
                    .name(name)
                    .subject(subject)
                    .difficult(difficult)
                    .duration(duration).build());

            log.info("Test {} has created", name);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&sub=all&order=name+asc&rows=5" +
                    "&message_success=The test created");
        } catch (ValidateException e) {
            log.warn("Test {} is invalid", name, e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&sub=all&order=name+asc&rows=5" +
                    "&name=" + name +
                    "&subject=" + subject +
                    "&difficult=" + difficult +
                    "&duration=" + duration +
                    "&message_bad_request=" + e.getMessage());
        } catch (DataBaseException e) {
            log.warn("Test {} have not updated", name, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
