package command;

import controllers.servlet.RequestHandler;
import models.Test;
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
        System.out.println("next page= " + page);
        System.out.println("next sub= " + sub);
        System.out.println("next rows= " + rows);
        System.out.println("next order= " + order);

        TestService testService = new TestService();
        int countPages = testService.countPages(sub, rows);
        System.out.println("page = " + page);
        page = page > countPages ? countPages : page;
        System.out.println("delete page= " +page);


        List<Test> tests;
        if(sub.equals("all")){
            tests = testService.nextPage(order,rows,page);
        } else {
            tests = testService.nextPage(sub, order, rows, page);
        }
        List<String> subjects = testService.getDistinctSubjects();
        List<String> sorts = Arrays.asList("difficult desc", "difficult asc", "name desc");

        req.getSession().setAttribute("subjects", subjects);
        req.getSession().setAttribute("orders", sorts);
        req.getSession().setAttribute("tests", tests);
        req.setAttribute("count_pages", countPages);

        req.setAttribute("page", page);

        req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);

    }
}
