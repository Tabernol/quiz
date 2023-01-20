package command.get;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class Prg implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            req.setAttribute(param.getKey(), param.getValue()[0]);
        }

        String servletPath = req.getParameter("servlet_path");
        req.getRequestDispatcher(servletPath).forward(req, resp);
    }
}
