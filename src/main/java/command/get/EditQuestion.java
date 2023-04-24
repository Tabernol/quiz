package command.get;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.AnswerDto;
import dto.QuestionDto;
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
import java.util.Set;

/**
 * EditQuestion.class is allowed only for admin.
 * The purpose of this class is to redirect to page for editing question.
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class EditQuestion extends AbstractCommand {

    /**
     * This method reads parameters.
     * He contacts the service level to get information about his question and answers for it.
     * Set this information in request and calls it.
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
        String questionId = req.getParameter(QUESTION_ID);

        setAttributesForRequest(req, TEST_ID, QUESTION_ID, PAGE);


        try {
            QuestionDto questionDto = questionService.get(Long.valueOf(questionId));
            Set<AnswerDto> answers = answerService.getAnswers(Long.valueOf(questionId));
            req.setAttribute(ANSWERS, answers);
            req.setAttribute(QUESTION, questionDto);

            log.info("Question with ID " + questionId + "has updated");
            req.getRequestDispatcher(EDIT_QUESTION).forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Question with ID " + questionId + " has NOT updated", e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }
    }
}
