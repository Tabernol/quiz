package command.post;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.QuestionDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.QuestionRepoImpl;
import servises.QuestionService;
import servises.impl.QuestionServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * EditQuestionPost.class is allowed only for admin.
 * The meaning of the class is to update a Question to an existing Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditQuestionPost extends AbstractCommand {

    /**
     * This method is read parameter from request.
     * It calls the service layer to update this Question
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
                        HttpServletResponse resp) throws
            ServletException, IOException {
        QuestionService questionService = AppContext.getInstance().getQuestionService();
        Long testId = Long.valueOf(req.getParameter(TEST_ID));
        Long questionId = Long.valueOf(req.getParameter(QUESTION_ID));
        String text = req.getParameter(TEXT);
        String page = req.getParameter(PAGE);

        try {
            questionService.update(QuestionDto.builder()
                    .id(questionId)
                    .testId(testId)
                    .text(text)
                    .build());
            log.info("Question with id {} has updated", questionId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_success=The question updated");

        } catch (DataBaseException e) {
            log.warn("Question with id {} has not updated", questionId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (ValidateException e) {
            log.info("Question with id {} is invalid", questionId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_question" +
                    "&test_id=" + testId +
                    "&question_id=" + questionId +
                    "&page=" + page +
                    "&message_bad_request=" + e.getMessage() +
                    "&tooLongAnswer=" + text);
        }
    }
}
