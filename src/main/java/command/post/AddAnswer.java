package command.post;

import command.AbstractCommand;
import context.AppContext;
import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.AnswerService;

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
public class AddAnswer extends AbstractCommand {

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
        AnswerService answerService = AppContext.getInstance().getAnswerService();

        Long testId = Long.valueOf(req.getParameter(TEST_ID));
        Long questionId = Long.valueOf(req.getParameter(QUESTION_ID));
        String page = req.getParameter(PAGE);
        String text = req.getParameter(TEXT);
        Boolean result = Boolean.valueOf(req.getParameter(RESULT));

        AnswerDto answerDto = AnswerDto.builder()
                .id(0L)
                .questionId(questionId)
                .text(text)
                .result(result)
                .build();

        try {
            answerService.create(answerDto);
            log.info("Answer for question id {} has added", questionId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_success=The answer added");

        } catch (DataBaseException e) {
            log.warn("Answer for question id {} has not added", questionId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (ValidateException e) {
            log.info("Answer for question id {} is invalid", questionId, e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&too_long_answer=" + text +
                    "&message_bad_request=" + e.getMessage());
        }
    }
}
