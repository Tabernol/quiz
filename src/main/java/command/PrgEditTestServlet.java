package command;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PrgEditTestServlet implements RequestHandler {
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
            req.setAttribute("message_question", req.getParameter("message_question"));
            req.setAttribute("message", req.getParameter("message"));
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        } else {
            req.setAttribute("message_question", "Something wrong");
            req.setAttribute("message", "Something wrong");
            EditTest editTest = new EditTest();
            editTest.execute(req, resp);
        }
    }
}
