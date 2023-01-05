package command.post;

import command.EditTest;
import command.FilterTests;
import command.NextPage;
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

        req.setAttribute("page", req.getParameter("page"));

        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        TestService testService = new TestService();

        if (testService.isNameExist(name)) {
            req.setAttribute("message", "this test name already exists");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else if (!DataValidator.validateForNamePlusNumber(name)) {
            req.setAttribute("message", "name must contains only liters and numbers and space from 2-20 items");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else if (!DataValidator.validateForName(subject)) {
            req.setAttribute("message", "subject must contains only liters and space from 2-20 items");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else if (!DataValidator.validateDifficult(difficult)) {
            req.setAttribute("message", "difficult must be from 1 to 100");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else if (!DataValidator.validateDuration(duration)) {
            req.setAttribute("message", "duration must be from 1 to 30 minutes");
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        } else {
            testService.createTest(name, subject, difficult, duration);
            req.setAttribute("message", "All Right))");
            NextPage nextPage = new NextPage();
            nextPage.execute(req, resp);
        }

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
        NextPage nextPage = new NextPage();
        nextPage.execute(req, resp);
    }
}
