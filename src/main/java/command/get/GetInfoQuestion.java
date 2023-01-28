package command.get;

import command.post.FinishTest;
import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
import models.Question;
import repo.AnswerRepo;
import repo.QuestionRepo;
import repo.ResultRepo;
import servises.AnswerService;
import servises.QuestionService;
import servises.ResultService;
import servises.ValidatorService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class GetInfoQuestion implements RequestHandler {
    QuestionService questionService;

    Integer index = 0;

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        AnswerService answerService = new AnswerService(new AnswerRepo(), new ValidatorService());
        ResultService resultService = new ResultService(new ResultRepo());
        req.setAttribute("duration", req.getParameter("duration"));

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        Integer size = (Integer) req.getSession().getAttribute("size");


        Integer numberQuestion = 0;
        if (req.getParameter("number_question") != null) {
            numberQuestion = Integer.valueOf(req.getParameter("number_question"));
        }


        String idQ = req.getParameter("id_question");
        String numQ = req.getParameter("number_question");
        String[] res = req.getParameterValues("res");

        System.out.println("id question " + idQ);
        System.out.println("number " + numQ);
        System.out.println(Arrays.toString(res));

        try {
            if (res != null) {
                boolean result = resultService.getResultByQuestion(Long.valueOf(idQ), res);
                List resultTest = (List<Boolean>) req.getSession().getAttribute("result_test");
                resultTest.add(result);
                req.getSession().setAttribute("result_test", resultTest);
                System.out.println("result " + result);
            }

        } catch (DataBaseException e) {
            //log
        }

        System.out.println("number " + numberQuestion);
        System.out.println("size " + size);
        if (size == numberQuestion) {
            //close timer
            System.out.println(" to finish");
            FinishTest finishTest = new FinishTest();
            finishTest.execute(req, resp);
        } else {
            long idQuestion = questions.get(numberQuestion).getId();

            List<Answer> answers = null;
            try {
                answers = answerService.getAnswers(idQuestion);
                String text = questions.get(numberQuestion).getText();

                int progress = numberQuestion * 100 / size;
                System.out.println("PROGRESS = " + progress);
                ++numberQuestion;



                PrintWriter writer = resp.getWriter();
                writer.print(
                        "<div class=\"progress\" role=\"progressbar\" aria-label=\"Basic example\" aria-valuenow=\"25\" aria-valuemin=\"0\" aria-valuemax=\"100\">\n" +
                                "    <div class=\"progress-bar\" style=\"width: " + progress + "%\">" +
                                progress + "%</div>\n" +
                                "</div>");
                writer.print(
                        "    <input id=\"id_question\" type=\"hidden\" " +
                                "name=\"id_question\" value=\"" + idQuestion + "\">\n" +
                                "    <input id=\"number_question\" type=\"hidden\" " +
                                "name=\"number_question\" value=\"" + numberQuestion + "\">");
                writer.print(
                        "<div class=\"row d-flex justify-content-center align-items-center h-3\">\n" +
                                "    <div class=\"col-6 col-md-6 col-lg-6 col-xl-6\">\n" +
                                "        <div class=\"card\" style=\"border-radius: 15px;\">\n" +
                                "            <div class=\"card-body p-4\">\n" +
                                "                <h5 class=\"text-center mb-0\">" + text + "</h5>\n" +
                                "            </div>\n" +
                                "        </div>\n" +
                                "    </div>\n" +
                                "</div>\n" +
                                "<br>");
                writer.print("<br>");
                for (Answer answer : answers) {
                    writer.print("<input type='checkbox' name='res' value='"
                            + answer.getId() + "'>" + answer.getText() + "<br>");
                }
                writer.print("<button type=\"button\"\n" +
                        "        onclick=\"loadQuestionAndAnswer(\n" +
                        "                document.getElementById('id_question').value,\n" +
                        "                document.getElementById('number_question').value,\n" +
                        "                document.getElementsByName('res'))\">Submit\n" +
                        "</button>");


            } catch (DataBaseException e) {
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
            }
        }


    }
}
