package command.post;

import command.EditTest;
import command.FilterTests;
import command.NextPage;
import command.ToCreateTest;
import controllers.filters.AuthorizationFilter;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.TestRepo;
import servises.TestService;
import util.ValidateMessage;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;

public class CreateTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(CreateTest.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // req.setAttribute("page", req.getParameter("page"));

        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        ValidateMessage validateMessage = new ValidateMessage();

        TestService testService = new TestService(new TestRepo());

        Integer success = 0;
        String message = validateMessage.checkFields(name, subject, difficult, duration);
        if (message.equals("All Right")) {
            try {
                int i = testService.createTest(name, subject, difficult, duration);
                req.setAttribute("message", "All Right))");
                if (i > 0) {
                    success = i;
                }
                logger.info("Test " + name + "has created");
                resp.sendRedirect(req.getContextPath() + "/prg_create_test" + "?suc=" + success + "&message=Test has created");
            } catch (DataBaseException e) {
                logger.info("Test " + name + "has not created");
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
        } else {
            logger.info("Test " + name + "is invalid");
            req.setAttribute("message", message);
            setPlaceHolder(req, resp, name, subject, difficult, duration);
        }


//        if (validateMessage.isNameExist(name)) {
//            req.setAttribute("message", "this test name already exists");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        } else if (!DataValidator.validateForNamePlusNumber(name)) {
//            req.setAttribute("message", "name must contains only liters and numbers and space from 2-20 items");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        } else if (!DataValidator.validateForName(subject)) {
//            req.setAttribute("message", "subject must contains only liters and space from 2-20 items");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        } else if (!DataValidator.validateDifficult(difficult)) {
//            req.setAttribute("message", "difficult must be from 1 to 100");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        } else if (!DataValidator.validateDuration(duration)) {
//            req.setAttribute("message", "duration must be from 1 to 30 minutes");
//            setPlaceHolder(req, resp, name, subject, difficult, duration);
//        } else {
//            try {
//                int i = testService.createTest(name, subject, difficult, duration);
//                req.setAttribute("message", "All Right))");
//                if (i > 0) {
//                    success = i;
//                }
//                resp.sendRedirect(req.getContextPath() + "/prg_create_test" + "?suc=" + success + "&message=Test has created");
//            } catch (DataBaseException e) {
//                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
//                throw new RuntimeException(e);
//            }
//        }
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
