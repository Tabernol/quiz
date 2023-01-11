package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Question;
import repo.QuestionRepo;
import repo.TestRepo;
import servises.QuestionService;
import servises.TestService;

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

        QuestionService questionService = new QuestionService(new QuestionRepo());
        TestService testService = new TestService(new TestRepo());
        List<Question> questions;
        Integer duration;


        try {
            testService.addPointPopularity(testId);
            duration = testService.get(testId).getDuration();
            questions = questionService.getAllById(Long.valueOf(testId));
        } catch (DataBaseException e) {
            req.getRequestDispatcher("/WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }
        Integer size = questions.size();
        //must start timer

        if (size > 0) {
            List<Boolean> resultTest = new ArrayList<>();
            req.getSession().setAttribute("size", size);
            req.getSession().setAttribute("test_id", testId);
            req.getSession().setAttribute("questions", questions);
            req.getSession().setAttribute("result_test", resultTest);
            req.setAttribute("duration", duration);
            req.setAttribute("number_question", 0);

            NextQuestion nextQuestion = new NextQuestion();
            nextQuestion.execute(req, resp);
        } else {
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("message", "Sorry, this test now is empty");
            req.getRequestDispatcher("/WEB-INF/view/student/page_test.jsp").forward(req, resp);
        }


    }
}
