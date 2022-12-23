package command;

import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllTests implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        TestService testService = new TestService();
        List<Test> all = testService.getAll();
//            req.getSession().setAttribute("tests", all);
        req.setAttribute("tests", all);

//        req.getRequestDispatcher("/WEB-INF/view/student/student_menu.jsp").forward(req, resp);

    }
}
