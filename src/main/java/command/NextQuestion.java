package command;

import controllers.servlet.RequestHandler;
import models.Answer;
import models.Question;
import servises.AnswerService;
import servises.QuestionService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class NextQuestion implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        AnswerService answerService = new AnswerService();

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");

        Integer numberQuestion = 0;
        if (req.getParameter("number_question") != null) {
            numberQuestion = Integer.valueOf(req.getParameter("number_question"));
        }

        System.out.println("number question = " + numberQuestion);

        long idQuestion = questions.get(numberQuestion).getId();
        List<Answer> answers = answerService.getAnswers(idQuestion);
        req.setAttribute("answers", answers);

        String text = questions.get(numberQuestion).getText();
        req.setAttribute("text", text);
        req.setAttribute("id_question", idQuestion);
        req.setAttribute("number_question", ++numberQuestion);

        req.getRequestDispatcher("/WEB-INF/view/student/page_question.jsp").forward(req, resp);

    }
}