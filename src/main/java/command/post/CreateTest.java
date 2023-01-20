package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.TestRepo;
import servises.TestService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateTest implements RequestHandler {
    private static Logger logger = LogManager.getLogger(CreateTest.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));

        TestService testService = new TestService(new TestRepo(), new ValidatorService());

        try {
            int result = testService.createTest(name, subject, difficult, duration);
            logger.info("Test " + name + "has created");
            resp.sendRedirect(req.getContextPath() + "/prg_create_test" + "?suc=" + result +
                    "&message=Test has created");
        } catch (ValidateException e) {
            logger.warn("Test " + name + " is invalid", e);
            setPlaceHolder(req, resp, name, subject, difficult, duration, e.getMessage());
        } catch (DataBaseException e) {
            logger.warn("Test " + name + "have not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }

    private void setPlaceHolder(HttpServletRequest req,
                                HttpServletResponse resp,
                                String name, String subject,
                                int difficult, int duration, String message) throws ServletException, IOException {

        resp.sendRedirect(req.getContextPath() + "/prg_create_test" +
                "?name=" + name +
                "&subject=" + subject +
                "&difficult=" + difficult +
                "&duration=" + duration +
                "&message=" + message);
    }
}
