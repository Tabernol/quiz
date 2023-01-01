package command.post;

import command.NextQuestion;
import controllers.servlet.RequestHandler;
import models.Answer;
import servises.AnswerService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultAnswer implements RequestHandler {
    AnswerService answerService = new AnswerService();

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String idQuestion = req.getParameter("id_question");
        String[] res = req.getParameterValues("res");

        System.out.println("id quest = " + idQuestion);
        System.out.println(Arrays.toString(res));

        Answer answer = null;
        List<Answer> userAnswers = new ArrayList<>();
        int count = -1;
        for (int i = 0; i < res.length; i++) {
            if (!res[i].equals("on")) {
                answer = new Answer();
                answer.setId(Long.parseLong(res[i]));
                answer.setResult(false);
                userAnswers.add(answer);
                count++;
            } else {
                userAnswers.get(count).setResult(true);
            }
        }

        List<Answer> trueAnswers = answerService.getAnswers(Long.valueOf(idQuestion));

        boolean result = true;
        for (int i = 0; i < userAnswers.size(); i++) {
            boolean contain = trueAnswers.contains(userAnswers.get(i));
            if (contain == false) {
                result = false;
                break;
            }
        }
        List resultTest = (List<Boolean>) req.getSession().getAttribute("result_test");
        resultTest.add(result);
        req.getSession().setAttribute("result_test", resultTest);

        Integer numberQuestion = Integer.valueOf(req.getParameter("number_question"));
        Integer size = (Integer) req.getSession().getAttribute("size");

        if(size == numberQuestion){
            FinishTest finishTest = new FinishTest();
            finishTest.execute(req, resp);
        }
        else {
            req.setAttribute("number_question", numberQuestion);
            NextQuestion nextQuestion = new NextQuestion();
            nextQuestion.execute(req, resp);
        }

    }
}
