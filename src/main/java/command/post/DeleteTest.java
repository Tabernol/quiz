package command.post;

import command.AllTests;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;
import servises.UserService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class DeleteTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        TestService testService = new TestService();
        Long id = Long.valueOf(req.getParameter("test_id"));
        testService.delete(id);

        List<Test> all = testService.getAll();
        req.getSession().setAttribute("tests", all);

        AllTests allTests = new AllTests();
        allTests.execute(req, resp);
    }
}
