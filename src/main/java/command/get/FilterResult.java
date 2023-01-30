package command.get;

import connection.MyDataSource;
import controllers.servlet.RequestHandler;
import dto.ResultDto;
import exeptions.DataBaseException;
import models.Test;
import repo.ResultRepo;
import repo.TestRepo;
import servises.ResultService;
import servises.TestService;
import servises.ValidatorService;
import util.MyTable;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

public class FilterResult implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");
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

        System.out.println("page = " + page);

        ResultService resultService = new ResultService(new ResultRepo());
        TestService testService = new TestService(new TestRepo(), new ValidatorService());
        List<String> subjects;
        int countPages;

        Long userId = (Long) req.getSession().getAttribute("user_id");
        try {
            countPages = resultService.getCountPagesResult(userId, Integer.valueOf(rows), sub);
            System.out.println("count page = " + countPages);

            List<ResultDto> pageResultList = resultService
                    .getPageResultList(userId, sub, order, Integer.valueOf(rows), Integer.valueOf(page));
            System.out.println(pageResultList);

            subjects = testService.getDistinctSubjects();
            req.getSession().setAttribute("subjects", subjects);
            req.setAttribute("user_result", pageResultList);
            req.setAttribute("count_pages", countPages);

            //    req.setAttribute("page", page);


            req.getRequestDispatcher("/WEB-INF/view/profile.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }
    }
}
