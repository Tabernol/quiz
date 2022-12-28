package command.post;

import command.AllTests;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateTest implements RequestHandler {

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        TestService testService = new TestService();

        req.setAttribute("tests", testService.getAll());

        if (!DataValidator.validateDifficult(difficult)) {
            req.setAttribute("message", "difficult must be from 1 to 100");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else if (!DataValidator.validateDuration(duration)) {
            req.setAttribute("message", "duration must be from 1 to 30 minutes");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        }

//        else if (!DataValidator.validateForNotLongString(name)) {
//            req.setAttribute("message", "Name is too long");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        } else if (!DataValidator.validateForNotLongString(subject)) {
//            req.setAttribute("message", "Subject is too long");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        }

        else {
            testService.createTest(name, subject, difficult, duration);
            req.getSession().setAttribute("tests", testService.getAll());
            req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);
//            AllTests allTests = new AllTests();
//            allTests.execute(req, resp);
        }
    }

    private void setPlaceHolder(HttpServletRequest req,
                                HttpServletResponse resp,
                                String name, String subject,
                                int difficult, int duration) throws ServletException, IOException {
        req.setAttribute("name", name);
        req.setAttribute("subject", subject);
        req.setAttribute("difficult", difficult);
        req.setAttribute("duration", duration);
        req.getRequestDispatcher("/WEB-INF/view/admin/admin_tests.jsp").forward(req, resp);
    }
}
