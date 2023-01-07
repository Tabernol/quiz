package command;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PrgCreateTest implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String suc = req.getParameter("suc");

        if (suc.equals("1")) {
            req.setAttribute("message", req.getParameter("message"));
            ToCreateTest toCreateTest = new ToCreateTest();
            toCreateTest.execute(req, resp);
        } else {
            req.setAttribute("message", "Something wrong");
            ToCreateTest toCreateTest = new ToCreateTest();
            toCreateTest.execute(req, resp);
        }
    }
}
