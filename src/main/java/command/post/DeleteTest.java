package command.post;

import command.FilterTests;
import command.NextPage;
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
        req.setAttribute("page", req.getParameter("page"));

        testService.delete(id);
        req.getSession().setAttribute("tests", testService.getAll());


//        FilterTests filterTests = new FilterTests();
//        filterTests.execute(req, resp);
        NextPage nextPage = new NextPage();
        nextPage.execute(req, resp);
    }
}
