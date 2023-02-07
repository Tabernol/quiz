package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Test;
import repo.TestRepo;
import servises.TestService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterTests implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String sub = req.getParameter("sub");
        String order = req.getParameter("order");
        String rows = req.getParameter("rows");
        String page = req.getParameter("page");

        if (sub == null || order == null || rows == null) {
            sub = (String) req.getSession().getAttribute("sub");
            order = (String) req.getSession().getAttribute("order");
            rows = (String) req.getSession().getAttribute("rows");
            if (sub == null || order == null || rows == null) {
                sub = "all";
                order = "name asc";
                rows = "5";
                HttpSession session = req.getSession();
                session.setAttribute("sub", sub);
                session.setAttribute("order", order);
                session.setAttribute("rows", rows);
                page = "1";
            }
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("sub", sub);
            session.setAttribute("order", order);
            session.setAttribute("rows", rows);
            page = "1";
        }
        if (page == null) {
            page = "1";
        }
//        =======================================

        TestService testService = new TestService(new TestRepo(), new ValidatorService(new DataValidator()));
        List<String> subjects;
        List<Test> filterTests;
        int countPages;

        try {
            countPages = testService.countPages(sub, Integer.valueOf(rows));

            filterTests = testService.getPageTestList(sub, order, Integer.valueOf(rows), Integer.valueOf(page));
            subjects = testService.getDistinctSubjects();

            req.getSession().setAttribute("subjects", subjects);
            req.getSession().setAttribute("tests", filterTests);
            req.setAttribute("count_pages", countPages);
            req.setAttribute("page", page);
            if (req.getSession().getAttribute("role").equals("admin")) {
                req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);
            } else {
                req.getRequestDispatcher("/WEB-INF/view/student/student_tests.jsp").forward(req, resp);
            }
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
