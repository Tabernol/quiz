package command.post;

import command.get.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddQuestion implements RequestHandler {

    private static Logger logger = LogManager.getLogger(AddQuestion.class);

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        String page = req.getParameter("page");
        req.setAttribute("test_id", testId);
        req.setAttribute("page", page);

        String text = req.getParameter("text");

        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService());
        try {
            questionService.addQuestion(testId, text);
            logger.info("Question for test id " + testId + "has added");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test"+
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_question=All Right)");

        } catch (DataBaseException e) {
            logger.warn("Question for test id " + testId + "has not added", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            logger.info("Question for test id " + testId + "is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test"+
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_question=" + "text of question is too long");
        }
    }
}
