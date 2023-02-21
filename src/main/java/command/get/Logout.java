package command.get;

import controllers.servlet.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Logout.class is responsibility for exit User from API
 * @author makskrasnopolskyi@gmail.com
 */
public class Logout implements RequestHandler {
    private static Logger logger = LogManager.getLogger(Logout.class);

    /**
     * This removes all parameters from the session and redirects to the index.jsp page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long userId = (Long) req.getSession().getAttribute("user_id");
        logger.info("User with Id " + userId + " logout");
        req.getSession().invalidate();
        req.getRequestDispatcher("/").forward(req, resp);
    }
}
