package controllers.filters;

import dao.connection.MyDataSource;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@WebFilter(filterName = "AbstractFilter", urlPatterns = {"/*"})
public abstract class AbstractFilter implements Filter {

  //  protected static final Logger LOGGER = LogManager.getLogger(ExceptionHandleFilter.class);



    public abstract void doCustomFilter(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        FilterChain filterChain) throws IOException, ServletException;

    @Override
    public void doFilter(ServletRequest servletRequest,
                         ServletResponse servletResponse,
                         FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        request.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        doCustomFilter(request, response, filterChain);
    }
}
