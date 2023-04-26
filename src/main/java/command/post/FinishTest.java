package command.post;

import command.AbstractCommand;
import context.AppContext;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import servises.ResultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * FinishTest.class is allowed only for student.
 * The meaning of the class is to calculate the result of the completed test, and show it
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class FinishTest extends AbstractCommand {

    /**
     * This method is read parameter from request.
     * It calls the service layer to calculate the result of the completed test
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
        ResultService resultService = AppContext.getInstance().getResultService();
        List<Boolean> result = (List<Boolean>) req.getSession().getAttribute(RESULT_TEST);
        Integer size = (Integer) req.getSession().getAttribute(SIZE);
        Long testId = (Long) req.getSession().getAttribute(TEST_ID);
        Long userId = (Long) req.getSession().getAttribute(USER_ID);


        try {
            Integer percentResult = resultService.getGrade(result, size);
            resultService.addResult(userId, testId, percentResult);
            log.info("User with id {} finished test with id {} with grade {}", userId, testId, percentResult);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/finish" +
                    "&percent_result=" + percentResult);
        } catch (DataBaseException e) {
            log.warn("User with id {} finish test with id {} has trouble finish", userId, testId);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }


    }
}
