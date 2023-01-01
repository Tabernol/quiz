package controllers.filters;

import command.Home;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

@WebFilter(filterName = "LanguageFilter", value = "/language")
public class LanguageFilter extends AbstractFilter{
    @Override
    public void doCustomFilter(HttpServletRequest req,
                               HttpServletResponse resp,
                               FilterChain filterChain) throws IOException, ServletException {
        String lang = req.getParameter("lang");
        req.getSession().setAttribute("lang", lang);

        if(req.getSession().getAttribute("role") == null){
            req.getRequestDispatcher("/").forward(req, resp);
        }
        else {
            Home home = new Home();
            home.execute(req, resp);
        }


    }
}
