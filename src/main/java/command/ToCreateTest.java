package command;

import controllers.servlet.RequestHandler;
import models.Subject;
import servises.SubjectService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ToCreateTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        SubjectService subjectService = new SubjectService();
        List<Subject> all = subjectService.getAll();
        System.out.println(all);

        req.getSession().setAttribute("subjects", all);
        req.getRequestDispatcher("/WEB-INF/view/admin/create_test.jsp").forward(req, resp);
    }
}
