package command.post;

import command.EditTest;
import controllers.servlet.RequestHandler;
import models.Test;
import servises.TestService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditTestPost implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
//        String sub = req.getParameter("sub");
//        String order = req.getParameter("order");
//        Integer rows = Integer.valueOf(req.getParameter("rows"));
////        String page = req.getParameter("page");
//        req.setAttribute("sub", sub);
//        req.setAttribute("order", order);
//        req.setAttribute("rows", rows);
//        req.setAttribute("page", page);


        Long testId = Long.valueOf(req.getParameter("test_id"));
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        TestService testService = new TestService();
        req.setAttribute("test_id", testId);

        if (!DataValidator.validateDifficult(difficult)) {
            req.setAttribute("message", "difficult must be from 1 to 100");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else if (!DataValidator.validateDuration(duration)) {
            req.setAttribute("message", "duration must be from 1 to 30 minutes");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        }
        else {
            testService.update(testId, name, subject, difficult, duration);
            req.setAttribute("message", "All Right)");
        }

        EditTest editTest = new EditTest();
        editTest.execute(req, resp);
    }



    private void setPlaceHolder(HttpServletRequest req,
                                HttpServletResponse resp,
                                String name, String subject,
                                int difficult, int duration) throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("name", name);
        req.setAttribute("subject", subject);
        req.setAttribute("difficult", difficult);
        req.setAttribute("duration", duration);
        EditTest editTest = new EditTest();
        editTest.execute(req, resp);
    }
}
