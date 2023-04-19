package command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public interface FilterSupport {

    String CURRENT_FILTER = "current_filter";

    default String readAndSetParameterForFilter(HttpServletRequest req, String param, String defaultValue) {
        HttpSession session = req.getSession();

        String currentFilter = (String) session.getAttribute(CURRENT_FILTER);
        String servletPath = req.getServletPath();

        if (currentFilter != null && currentFilter.equals(servletPath)) {
            String result = req.getParameter(param);
            if (result == null) {
                if (session.getAttribute(param) == null) {
                    session.setAttribute(param, defaultValue);
                    return defaultValue;
                } else {
                    result = (String) session.getAttribute(param);
                }
            } else {
                session.setAttribute(param, result);
            }
            return result;
        } else {
            session.setAttribute(param, defaultValue);
            return defaultValue;
        }
    }
}
