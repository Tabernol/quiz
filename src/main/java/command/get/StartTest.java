package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Question;
import repo.QuestionRepo;
import repo.TestRepo;
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

public class StartTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));

        QuestionService questionService = new QuestionService(new QuestionRepo(), new ValidatorService(new DataValidator()));
        TestService testService = new TestService(new TestRepo(), new ValidatorService(new DataValidator()));
        List<Question> questions = null;
        Integer duration = 0;
        Integer size = 0;

        try {
            testService.addPointPopularity(testId);
            duration = testService.get(testId).getDuration();
            questions = questionService.getAllById(Long.valueOf(testId));
            size = questions.size();
        } catch (DataBaseException e) {
            req.getRequestDispatcher("/WEB-INF/view/error_page.jsp").forward(req, resp);
        }
        //must start timer

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
            req.getRequestDispatcher("/WEB-INF/view/student/page_base_question.jsp").forward(req, resp);
//            NextQuestion nextQuestion = new NextQuestion();
//            nextQuestion.execute(req, resp);
        } else {
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("message", "Sorry, this test now is empty");
            req.getRequestDispatcher("/WEB-INF/view/student/page_test.jsp").forward(req, resp);
        }


    }
}
