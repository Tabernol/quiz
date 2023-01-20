package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Test;
import repo.TestRepo;
import servises.TestService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageTest implements RequestHandler {
    TestService testService ;
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        testService = new TestService(new TestRepo(), new ValidatorService());
        req.setAttribute("page", req.getParameter("page"));
        Long testId = Long.valueOf(req.getParameter("test_id"));


        Test test;
        try {
            test = testService.get(testId);
            req.setAttribute("test_id", test.getId());
            req.setAttribute("name", test.getName());
            req.setAttribute("subject", test.getSubject());
            req.setAttribute("difficult", test.getDifficult());
            req.setAttribute("duration", test.getDuration());
            req.getRequestDispatcher("/WEB-INF/view/student/page_test.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }

    }
}
