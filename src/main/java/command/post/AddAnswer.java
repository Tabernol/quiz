package command.post;

import controllers.servlet.RequestHandler;
import exeptions.DataBaseException;
import servises.AnswerService;
import servises.QuestionService;
import validator.DataValidator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddAnswer implements RequestHandler {
    AnswerService answerService = new AnswerService();
    QuestionService questionService = new QuestionService();

    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        Long testId = Long.valueOf(req.getParameter("test_id"));
        Long questionId = Long.valueOf(req.getParameter("question_id"));
        String page = req.getParameter("page");
        String text = req.getParameter("text");


        System.out.println("add = question id = " + questionId);

        Integer success = 0;
        if (!DataValidator.validateForNotLongString(text)) {
            req.setAttribute("test_id", testId);
            req.setAttribute("question_id", questionId);
            req.setAttribute("page", req.getParameter("page"));
            req.setAttribute("message_answer", "answer is too long");
            req.setAttribute("too_long_answer", text);
            req.getRequestDispatcher("/WEB-INF/view/admin/edit_question.jsp").forward(req, resp);
        } else {
            try {
                Boolean result = Boolean.valueOf(req.getParameter("result"));
                int i = answerService.createAnswer(questionId, text, result);
                if (i > 0) {
                    success = i;
                }
                resp.sendRedirect(req.getContextPath() + "/prg_edit_question_servlet" + "?" + "suc=" + success + "&test_id=" +
                        testId + "&question_id=" + questionId + "&page=" + page+"&message_answer=All Right");
            } catch (DataBaseException e) {
                req.getRequestDispatcher("WEB-INF/view/error_page.jsp").forward(req, resp);
                throw new RuntimeException(e);
            }

        }

    }

//    private void goTo(HttpServletRequest req, HttpServletResponse resp, Long testId, Long questionId, int success)
//            throws ServletException, IOException {
//        req.setAttribute("test_id", testId);
//        req.setAttribute("question_id", questionId);
//        req.setAttribute("page", req.getParameter("page"));
//
//        resp.sendRedirect(req.getContextPath() + "/prg_servlet"+"?" + "suc=" + success);

//        PrgServlet prgServlet = new PrgServlet();
//        prgServlet.execute(req, resp);

//        EditQuestion editQuestion = new EditQuestion();
//        editQuestion.execute(req, resp);
//    }
}
