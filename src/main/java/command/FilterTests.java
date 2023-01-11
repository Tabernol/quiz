package command;

import constans.Sort;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Test;
import repo.TestRepo;
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

        TestService testService = new TestService(new TestRepo());
        List<String> subjects;
        List<Test> filterTests;
        int countPages;

        try {
            countPages = testService.countPages(sub, Integer.valueOf(rows));
            filterTests = testService.getFilterTests(sub, order, Integer.valueOf(rows));
            subjects = testService.getDistinctSubjects();
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }

        req.getSession().setAttribute("subjects", subjects);
        req.getSession().setAttribute("tests", filterTests);
        req.setAttribute("count_pages", countPages);
        req.setAttribute("page", req.getParameter("page"));
        if (session.getAttribute("role").equals("admin")) {
            req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/student/student_tests.jsp").forward(req, resp);
        }
    }
}
