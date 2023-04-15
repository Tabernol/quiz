package command.get;

import controllers.servlet.RequestHandler;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout.class is responsibility for exit User from API
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class Logout implements RequestHandler {

    /**
     * This removes all parameters from the session and redirects to the index.jsp page
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute(USER_ID);
        log.info("User logout with Id {}", userId);
        req.getSession().invalidate();
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
