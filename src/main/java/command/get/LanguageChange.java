package command.get;

import controllers.servlet.RequestHandler;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * Language.class is responsible for changing the language of the interface in the API
 * This class is allowed for admin and student
 *
 * @author makskrasnopolskyi@gmail.com
 */
@Slf4j
public class LanguageChange implements RequestHandler {

    /**
     * This method reads the "locale" parameter,
     * changes it in the session, and redirects to the last page
     *
     * @param req  the HttpServletRequest object containing information about the request
     * @param resp the HttpServletResponse object for sending the response to the client
     * @throws ServletException if there is an error with the servlet
     * @throws IOException      if there is an I/O error
     */
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter(LOCALE);
        Locale locale;
        if (lang.equals(UA_LANG)) {
            locale = new Locale(UA_LANG, UA_COUNTRY);
        } else {
            locale = new Locale(EN_LANG, EN_COUNTRY);
        }
        req.getSession().setAttribute(LOCALE, locale);

        log.info("Language has changed to {}", lang);
        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
