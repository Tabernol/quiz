package command;

import constans.Sort;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FilterTests implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
       String role = (String) req.getSession().getAttribute("role");


        String sub = req.getParameter("sub");
        String order = req.getParameter("order");
        String rows = req.getParameter("rows");

        HttpSession session = req.getSession();

        session.setAttribute("sub", sub);
        session.setAttribute("order", order);
        session.setAttribute("rows", rows);

        TestService testService = new TestService();


        int countPages = testService.countPages(sub, Integer.valueOf(rows));

        List<Test> filterTests = testService.getFilterTests(sub, order, Integer.valueOf(rows));
        List<String> subjects = testService.getDistinctSubjects();
        List<String> sorts = Arrays.asList("difficult desc", "difficult asc", "name desc");

        req.getSession().setAttribute("subjects", subjects);
        req.getSession().setAttribute("orders", sorts);
        req.getSession().setAttribute("tests", filterTests);


        req.setAttribute("count_pages", countPages);
        req.setAttribute("page", req.getParameter("page"));

        req.getRequestDispatcher("/WEB-INF/view/" + role + "/" + role + "_tests.jsp").forward(req, resp);


    }
}
