package command.post;

import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.AnswerRepoImpl;
import servises.AnswerService;
import servises.impl.AnswerServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddAnswer.class is allowed only for admin.
 * The meaning of the class is to add an Answer to an existing Question in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class AddAnswer implements RequestHandler {
    private AnswerService answerService;

    /**
     * This method is read parameter from request.
     * It calls the service layer to create Answer
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
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
        answerService = AppContext.getInstance().getAnswerService();

        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String page = req.getParameter("page");
        String text = req.getParameter("text");
        Boolean result = Boolean.valueOf(req.getParameter("result"));

        AnswerDto answerDto = new AnswerDto(0L, questionId, text, result);

        try {
            answerService.createAnswer(answerDto);
            log.info("Answer for question id " + questionId + "has added");
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_success=The answer added");

        } catch (DataBaseException e) {
            log.warn("Answer for question id " + questionId + "has not added", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        } catch (ValidateException e) {
            log.info("Answer for question id " + questionId + "is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&too_long_answer=" + text +
                    "&message_bad_request=" + "answer is too long");
        }
    }
}
