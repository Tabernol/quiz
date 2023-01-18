package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.AnswerRepo;
import repo.QuestionRepo;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteAnswer implements RequestHandler {
    private static Logger logger = LogManager.getLogger(DeleteAnswer.class);

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService(new QuestionRepo());
        AnswerService answerService = new AnswerService(new AnswerRepo());
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        Long answerId = Long.valueOf(req.getParameter("answer_id"));
        String page = req.getParameter("page");

        try {
            answerService.deleteAnswer(answerId);
            req.setAttribute("answers", answerService.getAnswers(questionId));
            req.setAttribute("test_id", testId);
            req.setAttribute("question_id", questionId);
            req.setAttribute("question", questionService.get(questionId));
            req.setAttribute("page", req.getParameter("page"));
            logger.info("Answer with id " + answerId + " has deleted");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);

//            resp.sendRedirect(req.getContextPath()+"/edit_question" + "?page=" + page +
//                    "&question_id=" + questionId + "&test_id=" + testId);


        } catch (DataBaseException e) {
            logger.warn("Answer with id " + answerId + " has not delete");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
