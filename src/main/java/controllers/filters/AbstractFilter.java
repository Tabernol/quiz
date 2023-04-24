package controllers.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

//@WebFilter(filterName = "AbstractFilter", urlPatterns = {"/*"})

/**
 * An abstract base class for implementing Servlet Filters, providing a template for the {doFilter} method.
 * Custom filters should implement the {doCustomFilter} method,
 * which will be called by the {doFilter} method implemented in this class.
 */
public abstract class AbstractFilter implements Filter {

    protected static final String PASSWORD = "password";
    protected static final String LOGIN = "login";
    protected static final String NAME = "name";
    protected static final String ROLE = "role";
    protected static final String USER_ID = "user_id";
    protected static final  String ADMIN = "admin";
    protected static final String STUDENT = "student";
    protected static final String REPEAT_LOGIN = "repeat_login";

    protected static final String ERROR_PAGE = "WEB-INF/view/error_page.jsp";
    protected static final String LOGIN_FORM = "/WEB-INF/view/login_form.jsp";
    protected static final String MESSAGE_BAD_REQUEST = "message_bad_request";

    /**
     * The custom filter logic to be implemented by subclasses.
     *
     * @param req         the HTTP servlet request to be filtered
     * @param resp        the HTTP servlet response to be filtered
     * @param filterChain the filter chain for invoking the next filter or servlet in the chain
     * @throws IOException      if an input or output error occurs while the filter is processing the request or response
     * @throws ServletException if the processing of the request or response is interrupted by some error condition
     */
    public abstract void doCustomFilter(HttpServletRequest req,
                                        HttpServletResponse resp,
                                        FilterChain filterChain) throws IOException, ServletException;

    /**
     * Filters incoming requests and outgoing responses for encoding and invoking custom filter logic.
     *
     * @param servletRequest  the HTTP servlet request to be filtered
     * @param servletResponse the HTTP servlet response to be filtered
     * @param filterChain     the filter chain for invoking the next filter or servlet in the chain
     * @throws IOException      if an input or output error occurs while the filter is processing the request or response
     * @throws ServletException if the processing of the request or response is interrupted by some error condition
     */
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
