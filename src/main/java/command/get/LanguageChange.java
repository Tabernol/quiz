package command.get;

import controllers.servlet.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Language.class is responsible for changing the language of the interface in the API
 * @author makskrasnopolskyi@gmail.com
 */
public class LanguageChange implements RequestHandler {
    private static Logger logger = LogManager.getLogger(LanguageChange.class);

    /**
     * This method reads the "locale" parameter,
     * changes it in the session, and redirects to the last page
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter("locale");
        req.getSession().setAttribute("locale", lang);

        logger.info("Language has changed to " + lang);
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
