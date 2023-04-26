package command.post;

import command.AbstractCommand;
import context.AppContext;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import servises.AnswerService;
import servises.QuestionService;

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
public class DeleteAnswer extends AbstractCommand {

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

        Long questionId = Long.valueOf(req.getParameter(QUESTION_ID));
        Long answerId = Long.valueOf(req.getParameter(ANSWER_ID));

        try {
            answerService.delete(answerId);
            setAttributesForRequest(req, TEST_ID, QUESTION_ID, PAGE);
            req.setAttribute(ANSWERS, answerService.getAnswers(questionId));
            req.setAttribute(QUESTION, questionService.get(questionId));
            req.setAttribute(MESSAGE_SUCCESS, ANSWER_WAS_DELETED);

            log.info("Answer with id {} has deleted", answerId);
            req.getRequestDispatcher(EDIT_QUESTION).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Answer with id {} has not delete", answerId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }


    }
}
