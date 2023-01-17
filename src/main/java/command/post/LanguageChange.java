package command.post;

import controllers.servlet.RequestHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LanguageChange implements RequestHandler {
    @Override
    public void execute(HttpServletRequest req,
                        HttpServletResponse resp)
            throws ServletException, IOException {
        String lang = req.getParameter("locale");
        req.getSession().setAttribute("locale", lang);

        String referer = req.getHeader("referer");
        resp.sendRedirect(referer);
    }
}
