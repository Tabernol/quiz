package command;

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
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()){
            req.setAttribute(param.getKey(), param.getValue());
        }


        String suc = req.getParameter("suc");

        if (suc.equals("1")) {
            req.setAttribute("message_answer", req.getParameter("message_answer"));
            req.setAttribute("message", req.getParameter("message"));
            resp.addHeader("message_answer", req.getParameter("message_answer"));
            EditQuestion editQuestion = new EditQuestion();
            editQuestion.execute(req, resp);
        } else {
            req.setAttribute("message_answer", "Something wrong");
            EditQuestion editQuestion = new EditQuestion();
            editQuestion.execute(req, resp);
        }


    }
}
