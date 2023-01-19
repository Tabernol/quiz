package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.ResultRepo;
import servises.ResultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FinishTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(FinishTest.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        ResultService resultService = new ResultService(new ResultRepo());

        List<Boolean> result = (List<Boolean>) req.getSession().getAttribute("result_test");
        Integer size = (Integer) req.getSession().getAttribute("size");
        Long testId = (Long) req.getSession().getAttribute("test_id");
        Long userId = (Long) req.getSession().getAttribute("user_id");

        Integer percentResult = resultService.getGrade(result, size);
        req.setAttribute("percent_result", percentResult);

        try {
            resultService.addResult(userId, testId, percentResult);
            logger.info("User with id " + userId + " finished test with id " + testId + " with grade " + percentResult);
            req.getRequestDispatcher("/WEB-INF/view/student/page_finish.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            logger.warn("User with id " + userId + " finish test with id " + testId + " has trouble finish");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
