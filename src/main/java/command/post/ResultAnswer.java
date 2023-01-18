package command.post;

import command.NextQuestion;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
import repo.AnswerRepo;
import repo.ResultRepo;
import servises.AnswerService;
import servises.ResultService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ResultAnswer implements RequestHandler {
    AnswerService answerService = new AnswerService(new AnswerRepo());

    ResultService resultService = new ResultService(new ResultRepo());

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String questionId = req.getParameter("id_question");
        String[] res = req.getParameterValues("res");

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
            throw new RuntimeException(e);
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

//    private List<Answer> getUserAnswer(String[] res){
//        Answer answer = null;
//        List<Answer> userAnswers = new ArrayList<>();
//        int count = -1;
//        for (int i = 0; i < res.length; i++) {
//            if (!res[i].equals("on")) {
//                answer = new Answer();
//                answer.setId(Long.parseLong(res[i]));
//                answer.setResult(false);
//                userAnswers.add(answer);
//                count++;
//            } else {
//                userAnswers.get(count).setResult(true);
//            }
//        }
//        return userAnswers;
//    }


//    private boolean getResult(List<Answer> trueAnswers, String[] res ){
//        if(res == null || trueAnswers == null){
//            return true;
//        }
//        List<Answer> userAnswers = getUserAnswer(res);
//        boolean result = true;
//        for (int i = 0; i < userAnswers.size(); i++) {
//            boolean contain = trueAnswers.contains(userAnswers.get(i));
//            if (contain == false) {
//                result = false;
//                break;
//            }
//        }
//        return result;
//    }

}
