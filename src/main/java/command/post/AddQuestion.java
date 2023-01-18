package command.post;

import command.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Question;
import models.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.QuestionRepo;
import servises.QuestionService;
import servises.TestService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

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
        Integer success = 0;
        if (!DataValidator.validateForNotLongString(text)) {
            req.setAttribute("message_question", "text of question is too long");
            req.setAttribute("too_Long_Text", text);
            logger.info("Question for test id " + testId + "is invalid");
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        } else {
            try {
                QuestionService questionService = new QuestionService(new QuestionRepo());
                int i = questionService.addQuestion(testId, text);
                if (i > 0) {
                    success = i;
                }
                logger.info("Question for test id " + testId + "has added");
                resp.sendRedirect(req.getContextPath() + "/prg_edit_test_servlet" + "?" + "suc=" + success + "&test_id=" +
                        testId + "&page=" + page + "&message_question=All Right)");
            } catch (DataBaseException e) {
                logger.warn("Question for test id " + testId + "has not added");
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }

        }
    }
}
