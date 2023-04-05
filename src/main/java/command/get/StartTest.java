package command.get;

import controllers.servlet.RequestHandler;
import dto.QuestionDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Question;
import repo.impl.QuestionRepoImpl;
import repo.impl.TestRepoImpl;
import servises.QuestionService;
import servises.TestService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * StartTest.class is allowed only for student
 * This class is responsible for starting the selected test (quiz)
 */
@Slf4j
public class StartTest implements RequestHandler {
    private QuestionService questionService;
    private TestService testService;

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
        Long testId = Long.valueOf(req.getParameter("test_id"));

        questionService = new QuestionService(new QuestionRepoImpl(), new ValidatorService(new DataValidator()));
        testService = new TestService(new TestRepoImpl(), new ValidatorService(new DataValidator()));
        List<QuestionDto> questions = null;
        Integer duration = 0;
        Integer size = 0;

        try {
            testService.addPointPopularity(testId);
            duration = testService.get(testId).getDuration();
            questions = questionService.getAllById(Long.valueOf(testId));
            size = questions.size();
            log.info("give parameter test with id " + testId);
        } catch (DataBaseException e) {
            log.warn("problem with received parameter test with id " + testId, e);
            req.getRequestDispatcher("/WEB-INF/view/error_page.jsp").forward(req, resp);
        }


        if (size > 0) {
            List<Boolean> resultTest = new ArrayList<>();
            req.getSession().setAttribute("size", size);
            req.getSession().setAttribute("test_id", testId);
            req.getSession().setAttribute("questions", questions);
            req.getSession().setAttribute("result_test", resultTest);
            req.setAttribute("duration", duration);
            req.setAttribute("number_question", 0);

            GetInfoQuestion getInfoQuestion = new GetInfoQuestion();
            getInfoQuestion.execute(req, resp);
            log.info("Start test with id " + testId);
            req.getRequestDispatcher("/WEB-INF/view/student/page_base_question.jsp").forward(req, resp);
        } else {
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("message", "Sorry, this test now is empty");
            log.info("Test with id " + testId + " is empty. And not started");
            req.getRequestDispatcher("/WEB-INF/view/student/page_test.jsp").forward(req, resp);
        }


    }
}
