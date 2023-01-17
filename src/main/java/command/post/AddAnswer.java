package command.post;

import command.EditQuestion;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AnswerRepo;
import repo.QuestionRepo;
import servises.AnswerService;
import servises.QuestionService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnswer implements RequestHandler {
    private static Logger logger = LogManager.getLogger(AddAnswer.class);
    AnswerService answerService = new AnswerService(new AnswerRepo());
    QuestionService questionService = new QuestionService(new QuestionRepo());

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String page = req.getParameter("page");
        String text = req.getParameter("text");

        req.setAttribute("test_id", testId);
        req.setAttribute("question_id", questionId);
        req.setAttribute("page", req.getParameter("page"));


        Integer success = 0;
        if (!DataValidator.validateForNotLongString(text)) {
            req.setAttribute("message_answer", "answer is too long");
            req.setAttribute("too_long_answer", text);
            logger.info("Answer for question id " + questionId + "is invalid");
            EditQuestion editQuestion = new EditQuestion();
            editQuestion.execute(req, resp);
//            resp.sendRedirect(req.getContextPath()+"/edit_question" + "?page=" + page +
//                    "&question_id=" + questionId + "&test_id=" + testId);
        } else {
            try {
                Boolean result = Boolean.valueOf(req.getParameter("result"));
                int i = answerService.createAnswer(questionId, text, result);
                if (i > 0) {
                    success = i;
                }
                logger.info("Answer for question id " + questionId + "has added");
                resp.sendRedirect(req.getContextPath() + "/prg_edit_question_servlet" + "?" + "suc=" + success + "&test_id=" +
                        testId + "&question_id=" + questionId + "&page=" + page + "&message_answer=All Right");
            } catch (DataBaseException e) {
                logger.info("Answer for question id " + questionId + "has not added");
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }

        }

    }
}
