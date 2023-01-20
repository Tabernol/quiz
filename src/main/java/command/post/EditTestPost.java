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

public class EditTestPost implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditTestPost.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String name = req.getParameter("name");
        String subject = req.getParameter("subject");
        int difficult = Integer.parseInt(req.getParameter("difficult"));
        int duration = Integer.parseInt(req.getParameter("duration"));
        String page = req.getParameter("page");

        TestService testService = new TestService(new TestRepo(), new ValidatorService());

        req.setAttribute("test_id", testId);
        req.setAttribute("page", page);

        try {
            testService.update(testId, name, subject, difficult, duration);
            logger.info("Test with id " + testId + "has updated");
            resp.sendRedirect(req.getContextPath() + "/prg_edit_test_servlet" +
                    "?test_id=" + testId +
                    "&page=" + page +
                    "&message=All Right)");
        } catch (DataBaseException e) {
            logger.warn("Test with id " + testId + "has not updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Test with id " + testId + "is invalid, because ", e);
            resp.sendRedirect(req.getContextPath() + "/prg_edit_test_servlet" +
                    "?page=" + page +
                    "&test_id=" + testId +
                    "&message=" + e.getMessage());
        }
    }
}
