package command.get;

import command.get.EditQuestion;
import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PrgEditQuestionServlet implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String message = req.getParameter("message");
        String messageAnswer = req.getParameter("message_answer");
        String tooLongAnswer = req.getParameter("too_long_answer");

        req.setAttribute("message_answer", messageAnswer);
        req.setAttribute("message", message);
        req.setAttribute("too_long_answer", tooLongAnswer);
        EditQuestion editQuestion = new EditQuestion();
        editQuestion.execute(req, resp);


    }
}
