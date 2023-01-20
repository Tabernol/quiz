package command.get;

import command.get.EditTest;
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

        req.setAttribute("message_question", req.getParameter("message_question") );
        req.setAttribute("message", req.getParameter("message"));
        EditTest editTest = new EditTest();
        editTest.execute(req, resp);

    }
}
