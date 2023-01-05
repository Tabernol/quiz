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
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        TestService testService = new TestService();
        req.setAttribute("test_id", testId);
        req.setAttribute("page", req.getParameter("page"));


        if (!DataValidator.validateForNamePlusNumber(name)) {
            req.setAttribute("message", "name must contains only liters and numbers and space from 2-20 items");
            setPlaceHolder(req, resp);
        } else if (!DataValidator.validateForName(subject)) {
            req.setAttribute("message", "subject must contains only liters and space from 2-20 items");
            setPlaceHolder(req, resp);
        } else if (!DataValidator.validateDifficult(difficult)) {
            req.setAttribute("message", "difficult must be from 1 to 100");
            setPlaceHolder(req, resp);
        } else if (!DataValidator.validateDuration(duration)) {
            req.setAttribute("message", "duration must be from 1 to 30 minutes");
            setPlaceHolder(req, resp);
        } else {
            testService.update(testId, name, subject, difficult, duration);
            req.setAttribute("message", "All Right)");
            setPlaceHolder(req, resp);
        }
    }


    private void setPlaceHolder(HttpServletRequest req,
                                HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
        EditTest editTest = new EditTest();
        editTest.execute(req, resp);
    }
}
