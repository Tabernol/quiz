package command.get;

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
public class EditQuestion implements RequestHandler {
    private QuestionService questionService;
    private AnswerService answerService;
    private QuestionDto questionDto;
    private Set<AnswerDto> answers;

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
        questionService = AppContext.getInstance().getQuestionService();
        answerService = AppContext.getInstance().getAnswerService();
        String questionId = req.getParameter("question_id");

        req.setAttribute("test_id", req.getParameter("test_id"));
        req.setAttribute("question_id", req.getParameter("question_id"));
        req.setAttribute("page", req.getParameter("page"));


        try {
            questionDto = questionService.get(Long.valueOf(questionId));
            answers = answerService.getAnswers(Long.valueOf(questionId));
            req.setAttribute("answers", answers);
            req.setAttribute("question", questionDto);

            log.info("Question with ID " + questionId + "has updated");
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            log.warn("Question with ID " + questionId + " has NOT updated", e);
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
