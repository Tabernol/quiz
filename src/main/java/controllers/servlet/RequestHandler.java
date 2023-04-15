package controllers.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface RequestHandler {
    void execute(HttpServletRequest req,
                 HttpServletResponse resp)
            throws ServletException, IOException;

    default String readAndSetParameterForFilter(HttpServletRequest req, String param, String defaultValue) {
        HttpSession session = req.getSession();
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
    }

    String ERROR_PAGE = "WEB-INF/view/error_page.jsp";
    String EDIT_QUESTION = "/WEB-INF/view/admin/edit_question.jsp";
    String EDIT_TEST = "/WEB-INF/view/admin/edit_test.jsp";
    String ADMIN_IMAGES = "/WEB-INF/view/admin/admin_images.jsp";
    String PROFILE = "/WEB-INF/view/profile.jsp";
    String STUDENT_TESTS = "/WEB-INF/view/student/student_tests.jsp";
    String ADMIN_TESTS = "/WEB-INF/view/admin/admin_tests.jsp";
    String ADMIN_USERS = "/WEB-INF/view/admin/admin_users.jsp";
    String PAGE_FINISH = "/WEB-INF/view/student/page_finish.jsp";
    String MENU = "/WEB-INF/view/menu.jsp";
    String LOGIN_FORM = "/WEB-INF/view/login_form.jsp";
    String REGISTRATION = "/WEB-INF/view/registration.jsp";
    String PAGE_TEST = "/WEB-INF/view/student/page_test.jsp";
    String PAGE_BASE_QUESTION = "/WEB-INF/view/student/page_base_question.jsp";




    String SUB = "sub";
    String ORDER = "order";
    String ROWS = "rows";
    String PAGE = "page";
    String STATUS = "status";

    String DEFAULT_FILTER_ALL = "all";
    String DEFAULT_ORDER_NAME_ASC = "name asc";
    String DEFAULT_ROWS_5 = "5";
}
