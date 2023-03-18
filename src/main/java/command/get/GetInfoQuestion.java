package command.get;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import models.Answer;
import models.Question;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import repo.impl.AnswerRepoImpl;
import repo.impl.ResultRepoImpl;
import servises.AnswerService;
import servises.ResultService;
import servises.ValidatorService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
/**
 * GetInfoQuestion.class is allowed for student
 * The purpose of the class is to provide an information: text of question, answers for it, image for question
 *
 * @author makskrasnopolskyi@gmail.com
 */
public class GetInfoQuestion implements RequestHandler {

    private static Logger logger = LogManager.getLogger(GetInfoQuestion.class);

    Integer index = 0;

    /**
     * This method supply information: text of question, answers for it, image for question
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
        AnswerService answerService = new AnswerService(new AnswerRepoImpl(), new ValidatorService(new DataValidator()));
        ResultService resultService = new ResultService(new ResultRepoImpl());
        req.setAttribute("duration", req.getParameter("duration"));

        String idQuestion = req.getParameter("id_question");
        String numQ = req.getParameter("number_question");
        String[] res = req.getParameterValues("res");

        List<Question> questions = (List<Question>) req.getSession().getAttribute("questions");
        Integer size = (Integer) req.getSession().getAttribute("size");


        Integer numberQuestion = 0;
        if (req.getParameter("number_question") != null) {
            numberQuestion = Integer.valueOf(req.getParameter("number_question"));
        }

//
//        System.out.println("id question " + idQuestion);
//        System.out.println("number " + numQ);
//        System.out.println(Arrays.toString(res));

        try {
            if (res != null) {
                boolean result = resultService.getResultByQuestion(Long.valueOf(idQuestion), res);
                List resultTest = (List<Boolean>) req.getSession().getAttribute("result_test");
                resultTest.add(result);
                req.getSession().setAttribute("result_test", resultTest);
                System.out.println("result " + result);
                logger.info("Question = " + idQuestion + "\n" + "user result " + result);
            }
        } catch (DataBaseException e) {
            //log
        }

        System.out.println("number " + numberQuestion + "   size " + size);

        long nextIdQuestion = questions.get(numberQuestion).getId();

        List<Answer> answers = null;
        try {
            answers = answerService.getAnswers(nextIdQuestion);
            Collections.shuffle(answers);
            String text = questions.get(numberQuestion).getText();
            String urlImage = questions.get(numberQuestion).getUrlImage();

            int progress = numberQuestion * 100 / size;
            System.out.println("PROGRESS = " + progress);
            ++numberQuestion;


            PrintWriter writer = resp.getWriter();
            writer.print(
                    "<div class=\"progress\" role=\"progressbar\" aria-label=\"Basic example\" aria-valuenow=\"0\" aria-valuemin=\"0\" aria-valuemax=\"100\">\n" +
                            "    <div class=\"progress-bar\" style=\"width: " + progress + "%\">" +
                            progress + "%</div>\n" +
                            "</div>");
            writer.print(
                    "    <input id=\"id_question\" type=\"hidden\" " +
                            "name=\"id_question\" value=\"" + nextIdQuestion + "\">\n" +
                            "    <input id=\"number_question\" type=\"hidden\" " +
                            "name=\"number_question\" value=\"" + numberQuestion + "\">");

            if (urlImage != null) {
                writer.print("<img src=\"" + urlImage + "\"" +
                        " width=\"256\" height=\"256\" class=\"rounded mx-auto d-block\" alt=\"img\"/>");
            }

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
            writer.print("<div class=\"d-flex justify-content-center\">\n" +
                    "    <div class=\"col-5 col-md-5 col-lg-5 col-xl-5\">\n" +
                    "        <div class=\"card\" style=\"border-radius: 15px;\">\n" +
                    "            <div class=\"card-body p-4\">\n" +
                    "                <div class=\"d-grid gap-2\">");
            for (Answer answer : answers) {
                writer.print(" <input type=\"checkbox\" class=\"btn-check\" " +
                        "id=\"btncheck" + answer.getId() + "\" name=\"res\" value=\"" + answer.getId() +
                        "\" autocomplete=\"off\">\n" +
                        "<label class=\"btn btn-outline-success\" for=\"btncheck" +
                        answer.getId() + "\">" + answer.getText() + "</label>");
            }
            writer.print("  </div>\n" +
                    "                <br>\n" +
                    "                <div class=\"d-flex justify-content-end\">\n" +
                    "                    <button type=\"btn btn-primary\" onclick=\"loadQuestionAndAnswer(\n" +
                    "                    document.getElementById('id_question').value,\n" +
                    "                    document.getElementById('number_question').value,\n" +
                    "                    document.getElementsByName('res'))\">Submit\n" +
                    "                    </button>\n" +
                    "                </div>\n" +
                    "            </div>\n" +
                    "        </div>\n" +
                    "    </div>\n" +
                    "</div>");
        } catch (DataBaseException e) {
            logger.warn("Problem with supply question-answer");
            req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
        }
    }
}
