package command.get;

import command.AbstractCommand;
import controllers.servlet.RequestHandler;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

/**
 * Prg.class implements protection against resubmitting a request
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class Prg extends AbstractCommand {

    /**
     * This method reads all parameters from the request, sets them in the next request.
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Map<String, String[]> parameterMap = req.getParameterMap();
        for (Map.Entry<String, String[]> param : parameterMap.entrySet()) {
            req.setAttribute(param.getKey(), param.getValue()[0]);
        }
        String servletPath = req.getParameter(SERVLET_PATH);
        log.info("Command PRG works with servlet path {}", servletPath);
        req.getRequestDispatcher(servletPath).forward(req, resp);
    }
}
