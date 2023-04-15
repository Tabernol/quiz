package controllers.servlet;

import javax.print.DocFlavor;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public interface RequestHandler {
    void execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException;

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

    //==============PATH to file====================
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
    //=====================
    //==========Parameter of request==============
    String USER_ID = "user_id";
    String TEST_ID = "test_id";
    String QUESTION_ID = "question_id";
    String ANSWER_ID = "answer_id";
    String PUBLIC_ID = "public_id";

    String SERVLET_PATH = "servlet_path";
    String MESSAGE = "message";
    String MESSAGE_SUCCESS = "message_success";

    String MESSAGE_BAD_REQUEST = "message_bad_request";
    String SIZE = "size";
    String QUESTION = "question";
    String NUMBER_QUESTION = "number_question";
    String NAME = "name";
    String SUBJECT = "subject";
    String DIFFICULT = "difficult";
    String DURATION = "duration";
    String CLOUDINARY = "cloudinary";
    String URL = "url";
    String WIDTH = "width";
    String HEIGHT = "height";
    String WEB_INF = "WEB-INF";
    String REPEAT_NAME = "repeat_name";
    String REPEAT_LOGIN = "repeat_login";
    String REPEAT_PASSWORD = "repeat_password";
    String PASSWORD = "password";
    String LOGIN = "login";

    String ANSWERS = "answers";
    String USERS = "users";
    String QUESTIONS = "questions";
    String SUBJECTS = "subjects";
    String IMAGES = "images";
    String TESTS = "tests";

    String TEXT = "text";
    String RESULT = "result";
    String TEST = "test";
    String ROLE = "role";
    String ADMIN = "admin";
    String STUDENT = "student";
    String USER = "user";

    String USER_RESULT = "user_result";
    String RESULT_TEST = "result_test";
    String COUNT_PAGES = "count_pages";


    String SUB = "sub";
    String ORDER = "order";
    String ROWS = "rows";
    String PAGE = "page";
    String STATUS = "status";

    String LOCALE = "locale";
    String UA_LANG = "ua";
    String UA_COUNTRY = "UA";
    String EN_LANG = "en";
    String EN_COUNTRY = "UK";
    //==================================
    String DEFAULT_FILTER_ALL = "all";
    String DEFAULT_ORDER_NAME_ASC = "name asc";
    String DEFAULT_ROWS_5 = "5";
    //=========================

    String ANSWER_WAS_DELETED = "The answer was deleted";
}
