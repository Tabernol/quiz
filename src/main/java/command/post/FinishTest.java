package command.post;

import controllers.servlet.RequestHandler;
import servises.ResultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class FinishTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        ResultService resultService = new ResultService();
        List<Boolean> result = (List<Boolean>) req.getSession().getAttribute("result_test");
        long count = result.stream().filter(bool -> bool.equals(true)).count();
        System.out.println("count = " + count);

        Integer size = (Integer) req.getSession().getAttribute("size");
        Long testId = (Long) req.getSession().getAttribute("test_id");
        Long userId = (Long) req.getSession().getAttribute("user_id");

        Integer percentResult = Math.toIntExact(count * 100 / size);
        System.out.println(percentResult);

        resultService.addResult(userId,testId,percentResult);

        req.setAttribute("percent_result", percentResult);

        req.getRequestDispatcher("/WEB-INF/view/student/page_finish.jsp").forward(req, resp);

    }
}