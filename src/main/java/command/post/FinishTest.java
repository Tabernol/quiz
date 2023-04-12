package command.post;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.ResultRepoImpl;
import servises.ResultService;
import servises.impl.ResultServiceImpl;

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
public class FinishTest implements RequestHandler {
    private ResultService resultService;

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
        resultService = AppContext.getInstance().getResultService();
        List<Boolean> result = (List<Boolean>) req.getSession().getAttribute("result_test");
        Integer size = (Integer) req.getSession().getAttribute("size");
        Long testId = (Long) req.getSession().getAttribute("test_id");
        Long userId = (Long) req.getSession().getAttribute("user_id");


        try {
            Integer percentResult = resultService.getGrade(result, size);
            resultService.addResult(userId, testId, percentResult);
            log.info("User with id " + userId + " finished test with id " + testId + " with grade " + percentResult);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/finish" +
                    "&percent_result=" + percentResult);
        } catch (DataBaseException e) {
            log.warn("User with id " + userId + " finish test with id " + testId + " has trouble finish");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
