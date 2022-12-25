package command;

import constans.Sort;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FilterTests implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String sub = req.getParameter("sub");
        String order = req.getParameter("order");


        TestService testService = new TestService();
        List<Test> filterTests = testService.getFilterTests(sub, order);
        req.setAttribute("tests", filterTests);
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);


    }
}
