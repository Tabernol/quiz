package command.post;

import command.get.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.TestRepo;
import servises.TestService;
import util.ValidateMessage;

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

        ValidateMessage validateMessage = new ValidateMessage();
        TestService testService = new TestService(new TestRepo());

        req.setAttribute("test_id", testId);
        req.setAttribute("page", page);

        Integer success = 0;

        String message = null;
        if (!validateMessage.checkFieldsTest(name, subject, difficult, duration)) {
            message = validateMessage.getMessageIfInvalid(name, subject, difficult, duration);
            req.setAttribute("message", message);
            // resp.sendRedirect(req.getRequestURI() + "?message=" + message);
            logger.info("Test with id " + testId + "is invalid, because " + message);

            resp.sendRedirect(req.getContextPath() + "/edit_test" + "?page=" + page
                    + "&test_id=" + testId + "&message=" + message);

//            EditTest editTest = new EditTest();
//            editTest.execute(req, resp);
        } else {
            try {
                int update = testService.update(testId, name, subject, difficult, duration);
                if (update > 0) {
                    success = update;
                }
                logger.info("Test with id " + testId + "has updated");
                resp.sendRedirect(req.getContextPath() + "/prg_edit_test_servlet" + "?" + "suc=" + success + "&test_id=" +
                        testId + "&page=" + page + "&message=All Right)");
                //  req.setAttribute("message", "All Right)");
            } catch (DataBaseException e) {
                logger.warn("Test with id " + testId + "has not updated");
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
        }
    }
}
