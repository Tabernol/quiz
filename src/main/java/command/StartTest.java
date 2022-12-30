package command;

import controllers.servlet.RequestHandler;
import models.Question;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class StartTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        QuestionService questionService = new QuestionService();

        String testId = req.getParameter("test_id");

        List<Question> questions = questionService.getAllById(Long.valueOf(testId));
        Integer size = questions.size();



        req.getSession().setAttribute("size", size);
        req.getSession().setAttribute("test_id", testId);
        req.getSession().setAttribute("questions", questions);
        req.setAttribute("number_question", 0);

        //must start timer

        System.out.println("page_q  test id = " + testId);
        System.out.println("rageQ size  " + size);


        if (size > 0){
            NextQuestion nextQuestion = new NextQuestion();
            nextQuestion.execute(req, resp);
        } else {
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("message", "Sorry, this test now is empty");
            req.getRequestDispatcher("/WEB-INF/view/student/page_test.jsp").forward(req, resp);
        }



    }
}
