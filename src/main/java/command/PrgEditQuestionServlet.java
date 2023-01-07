package command;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrgEditQuestionServlet implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        req.setAttribute("page", req.getParameter("page"));
        req.setAttribute("test_id", req.getParameter("test_id"));
        req.setAttribute("question_id", req.getParameter("question_id"));
        String suc = req.getParameter("suc");

        if (suc.equals("1")) {
            req.setAttribute("message_answer", req.getParameter("message_answer"));
            req.setAttribute("message", req.getParameter("message"));
            EditQuestion editQuestion = new EditQuestion();
            editQuestion.execute(req, resp);
        } else {
            req.setAttribute("message_answer", "Something wrong");
            EditQuestion editQuestion = new EditQuestion();
            editQuestion.execute(req, resp);
        }


    }
}
