package command.post;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.QuestionDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * AddQuestion.class is allowed only for admin.
 * The meaning of the class is to add a Question to an existing Test(quiz) in database.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class AddQuestion extends AbstractCommand {

    /**
     * This method is read parameter from request.
     * It calls the service layer to create Question
     * if DataBaseException is caught, redirects to error page.
     * if ValidateException is caught, redirects to the page from which the request was made
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = AppContext.getInstance().getQuestionService();
        Long testId = Long.valueOf(req.getParameter(TEST_ID));
        String page = req.getParameter(PAGE);
        String text = req.getParameter(TEXT);

        try {
            questionService.create(QuestionDto.builder()
                    .testId(testId)
                    .text(text)
                    .build());
            log.info("Question for test id {} has added ", testId);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test" +
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_success=Question added");

        } catch (DataBaseException e) {
            log.warn("Question for test id {} has not added", testId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        } catch (ValidateException e) {
            log.info("Question for test id " + testId + "is invalid", e);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/edit_test" +
                    "&test_id=" + testId +
                    "&page=" + page +
                    "&message_bad_request=" + "text of question is too long");
        }
    }
}
