package command.post;

import command.get.NextQuestion;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import repo.AnswerRepo;
import repo.ResultRepo;
import servises.AnswerService;
import servises.ResultService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ResultAnswer implements RequestHandler {
    AnswerService answerService = new AnswerService(new AnswerRepo(), new ValidatorService(new DataValidator()));

    ResultService resultService = new ResultService(new ResultRepo());

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String questionId = req.getParameter("id_question");
        String[] res = req.getParameterValues("res");
        Integer numberQuestion = Integer.valueOf(req.getParameter("number_question"));
        Integer size = (Integer) req.getSession().getAttribute("size");

//        List<Answer> trueAnswers = null;
//        try {
//            trueAnswers = answerService.getAnswers(Long.valueOf(idQuestion));
//        } catch (DataBaseException e) {
//            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
//            throw new RuntimeException(e);
//        }

//        boolean result = getResult(trueAnswers, res);
        boolean result = false;
        try {
            result = resultService.getResultByQuestion(Long.valueOf(questionId), res);
        } catch (DataBaseException e) {
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }

        List resultTest = (List<Boolean>) req.getSession().getAttribute("result_test");
        resultTest.add(result);
        req.getSession().setAttribute("result_test", resultTest);

        if (size == numberQuestion) {
            FinishTest finishTest = new FinishTest();
            finishTest.execute(req, resp);
        }
//        else {
//            req.setAttribute("number_question", numberQuestion);
//            NextQuestion nextQuestion = new NextQuestion();
//            nextQuestion.execute(req, resp);
//        }

    }

}
