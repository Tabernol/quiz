package command.post;

import command.EditTest;
import command.FilterTests;
import command.NextPage;
import command.ToCreateTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Test;
import servises.TestService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CreateTest implements RequestHandler {
    Logger logger = Logger.getLogger(CreateTest.class.getName());

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // req.setAttribute("page", req.getParameter("page"));

        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        TestService testService = new TestService();
        boolean isNameExist;

        try {
            isNameExist = testService.isNameExist(name);
        } catch (DataBaseException e) {
            logger.log(Level.INFO, "can not creat test");
            throw new RuntimeException(e);
        }

        Integer success = 0;
        if (isNameExist) {
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
            try {
                int i = testService.createTest(name, subject, difficult, duration);
                req.setAttribute("message", "All Right))");
                if (i > 0) {
                    success = i;
                }
                resp.sendRedirect(req.getContextPath() + "/prg_create_test" + "?suc=" + success + "&message=Test has created");
            } catch (DataBaseException e) {
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
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
        ToCreateTest toCreateTest = new ToCreateTest();
        toCreateTest.execute(req, resp);
    }
}
