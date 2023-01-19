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
        String suc = req.getParameter("suc");

        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()){
            req.setAttribute(param.getKey(), param.getValue());
        }


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
