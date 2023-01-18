package command.post;

import command.EditQuestion;
import command.EditTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
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
import java.util.List;

public class EditQuestionPost implements RequestHandler {
    private static Logger logger = LogManager.getLogger(EditQuestionPost.class);
    QuestionService questionService = new QuestionService(new QuestionRepo());
    AnswerService answerService = new AnswerService(new AnswerRepo());

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp) throws
            ServletException, IOException {

        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String text = req.getParameter("text");
        String page = req.getParameter("page");

//        req.setAttribute("test_id", req.getParameter("test_id"));
//        req.setAttribute("question_id", req.getParameter("question_id"));
//        req.setAttribute("page", page);


        Integer success = 0;
        if (!DataValidator.validateForNotLongString(text)) {
            req.setAttribute("message", "text of question is too long");
            req.setAttribute("tooLongAnswer", text);
            logger.info("Question with id " + questionId + "is invalid");
            EditQuestion editQuestion = new EditQuestion();
            editQuestion.execute(req, resp);
//            resp.sendRedirect(req.getRequestURL() + "?page=" + page +
//                    "&question_id=" + questionId + "&test_id=" + testId);
        } else {
            try {
                int update = questionService.update(text, questionId);
                if (update > 0) {
                    success = update;
                }
                logger.info("Question with id " + questionId + "has updated");
                resp.sendRedirect(req.getContextPath() + "/prg_edit_question_servlet" + "?" + "suc=" + success + "&test_id=" +
                        testId + "&question_id=" + questionId + "&page=" + page + "&message=All Right");
                //  goTo(req, resp, testId, questionId);
            } catch (DataBaseException e) {
                logger.warn("Question with id " + questionId + "has not updated");
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }
        }


    }
}
