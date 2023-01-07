package command.post;

import command.EditTest;
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

public class EditTestPost implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        String page = req.getParameter("page");

        TestService testService = new TestService();
        req.setAttribute("test_id", testId);
        req.setAttribute("page", page);

        Integer success = 0;
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
            try {
                int update = testService.update(testId, name, subject, difficult, duration);
                if (update > 0) {
                    success = update;
                }
                resp.sendRedirect(req.getContextPath() + "/prg_edit_test_servlet" + "?" + "suc=" + success + "&test_id=" +
                        testId + "&page=" + page + "&message=All Right)");
                //  req.setAttribute("message", "All Right)");
            } catch (DataBaseException e) {
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
        }
    }


    private void setPlaceHolder(HttpServletRequest req,
                                HttpServletResponse resp) throws ServletException, IOException {
        //req.setAttribute("page", req.getParameter("page"));
        EditTest editTest = new EditTest();
        editTest.execute(req, resp);
    }
}
