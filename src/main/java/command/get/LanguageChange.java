package command.get;

import controllers.servlet.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageChange implements RequestHandler {

    private static Logger logger = LogManager.getLogger(LanguageChange.class);

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
