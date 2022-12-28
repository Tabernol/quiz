package command.post;

import command.AllTests;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditTestPost implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        TestService testService = new TestService();
        testService.update(testId, name, subject, difficult, duration);


        List<Test> all = testService.getAll();
        req.getSession().setAttribute("tests", all);

        AllTests allTests = new AllTests();
        allTests.execute(req, resp);
    }
}