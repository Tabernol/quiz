package command.get;

import command.AbstractCommand;
import controllers.AppContext;
import controllers.servlet.RequestHandler;
import dto.QuestionDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.impl.QuestionRepoImpl;
import repo.impl.TestRepoImpl;
import servises.QuestionService;
import servises.TestService;
import servises.impl.QuestionServiceImpl;
import servises.impl.TestServiceImpl;
import servises.impl.ValidatorServiceImpl;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * StartTest.class is allowed only for student
 * This class is responsible for starting the selected test (quiz)
 */
@Slf4j
public class StartTest extends AbstractCommand {

    /**
     * This method starts test(quiz) for user
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
        Long testId = Long.valueOf(req.getParameter(TEST_ID));
        HttpSession session = req.getSession();

        QuestionService questionService = AppContext.getInstance().getQuestionService();
        TestService testService = AppContext.getInstance().getTestService();

        List<QuestionDto> questions = null;
        Integer duration = 0;
        Integer size = 0;

        try {
            testService.addPointPopularity(testId);
            duration = testService.get(testId).getDuration();
            questions = questionService.getAllById(testId);
            size = questions.size();
            log.info("give parameter test with id {}", testId);
        } catch (DataBaseException e) {
            log.warn("problem with received parameter test with id {}", testId, e);
            req.getRequestDispatcher(ERROR_PAGE).forward(req, resp);
        }


        if (size > 0) {
            List<Boolean> resultTest = new ArrayList<>();
            session.setAttribute(SIZE, size);
            session.setAttribute(TEST_ID, testId);
            session.setAttribute(QUESTIONS, questions);
            session.setAttribute(RESULT_TEST, resultTest);
            req.setAttribute(DURATION, duration);
            req.setAttribute(NUMBER_QUESTION, 0);

            GetInfoQuestion getInfoQuestion = new GetInfoQuestion();
            getInfoQuestion.execute(req, resp);
            log.info("Start test with id {}", testId);
            req.getRequestDispatcher(PAGE_BASE_QUESTION).forward(req, resp);
        } else {
//            req.setAttribute(PAGE, req.getParameter(PAGE));
//            req.setAttribute(MESSAGE_BAD_REQUEST, "Sorry, this test is empty now");

            log.info("Test with id {} is empty. And not started", testId);
//            req.getRequestDispatcher(STUDENT_TESTS).forward(req,resp);
            resp.sendRedirect(req.getContextPath() + "/prg" +
                    "?servlet_path=/filter_tests" +
                    "&page=" + req.getParameter(PAGE) +
                    "&" + MESSAGE_BAD_REQUEST + "=Sorry, this test is empty now");
        }
    }
}
