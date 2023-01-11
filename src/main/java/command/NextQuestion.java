package command;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
import models.Question;
import repo.AnswerRepo;
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
        AnswerService answerService = new AnswerService(new AnswerRepo());

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");

        Integer numberQuestion = 0;
        if (req.getParameter("number_question") != null) {
            numberQuestion = Integer.valueOf(req.getParameter("number_question"));
        }

        System.out.println("number question = " + numberQuestion);

        long idQuestion = questions.get(numberQuestion).getId();
        List<Answer> answers = null;
        try {
            answers = answerService.getAnswers(idQuestion);
            req.setAttribute("answers", answers);

            String text = questions.get(numberQuestion).getText();
            req.setAttribute("text", text);
            req.setAttribute("id_question", idQuestion);
            req.setAttribute("number_question", ++numberQuestion);
            req.setAttribute("duration", req.getParameter("duration"));

            req.getRequestDispatcher("/WEB-INF/view/student/page_base_question.jsp").forward(req, resp);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            throw new RuntimeException(e);
        }


    }
}
