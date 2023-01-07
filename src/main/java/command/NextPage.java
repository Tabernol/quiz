package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Test;
import servises.PaginationService;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class NextPage implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Integer page = Integer.valueOf(req.getParameter("page"));
        String sub = (String) req.getSession().getAttribute("sub");
        String order = (String) req.getSession().getAttribute("order");
        String stringRows = (String) req.getSession().getAttribute("rows");
        Integer rows = Integer.valueOf(stringRows);

        TestService testService = new TestService();
        PaginationService paginationService = new PaginationService();
        List<Test> tests;
        List<String> subjects;
        int countPages;

        try {
            countPages = testService.countPages(sub, rows);
            page = page > countPages ? countPages : page;

            if (sub.equals("all")) {
                tests = paginationService.nextPage(order, rows, page);
            } else {
                tests = paginationService.nextPage(sub, order, rows, page);
            }
            subjects = testService.getDistinctSubjects();
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }

        req.getSession().setAttribute("subjects", subjects);
//        req.getSession().setAttribute("order", order);
        req.getSession().setAttribute("tests", tests);
        req.setAttribute("count_pages", countPages);
        req.setAttribute("page", page);

        String role = (String) req.getSession().getAttribute("role");
        req.getRequestDispatcher("/WEB-INF/view/" + role + "/" + role + "_tests.jsp").forward(req, resp);

    }
}
