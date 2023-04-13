package command.post;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.AnswerRepoImpl;
import repo.impl.QuestionRepoImpl;
import servises.AnswerService;
import servises.QuestionService;
import servises.impl.AnswerServiceImpl;
import servises.impl.QuestionServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * DeleteAnswer.class is allowed only for admin.
 * The meaning of the class is to delete an Answer in an existing Question in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class DeleteAnswer implements RequestHandler {

    /**
     * This method is read parameter from request.
     * It calls the service layer to delete an Answer
     * if DataBaseException is caught, redirects to error page.
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = AppContext.getInstance().getQuestionService();
        AnswerService answerService = AppContext.getInstance().getAnswerService();

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
            req.setAttribute("page", page);
            req.setAttribute("message_success", "The answer was deleted");

            log.info("Answer with id " + answerId + " has deleted");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Answer with id " + answerId + " has not delete", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }


    }
}
