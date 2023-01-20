package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class PrgCreateTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setAttribute("name", req.getParameter("name"));
        req.setAttribute("subject", req.getParameter("subject"));
        req.setAttribute("difficult", req.getParameter("difficult"));
        req.setAttribute("duration", req.getParameter("duration"));
        req.setAttribute("message", req.getParameter("message"));

        ToCreateTest toCreateTest = new ToCreateTest();
        toCreateTest.execute(req, resp);

    }
}
