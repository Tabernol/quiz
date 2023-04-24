package command;

import controllers.servlet.RequestHandler;

import javax.servlet.http.HttpServletRequest;
import java.util.stream.Stream;

public abstract class AbstractCommand implements RequestHandler {

    protected void setAttributesForRequest(HttpServletRequest req, String... parameters) {
        Stream.of(parameters)
                .forEach(param -> req.setAttribute(param, req.getParameter(param)));
    }

    //==============PATH to file====================
    public static final String ERROR_PAGE = "WEB-INF/view/error_page.jsp";
    public static final String EDIT_QUESTION = "/WEB-INF/view/admin/edit_question.jsp";
    public static final String EDIT_TEST = "/WEB-INF/view/admin/edit_test.jsp";
    public static final String ADMIN_IMAGES = "/WEB-INF/view/admin/admin_images.jsp";
    public static final  String PROFILE = "/WEB-INF/view/profile.jsp";
    public static final  String STUDENT_TESTS = "/WEB-INF/view/student/student_tests.jsp";
    public static final String ADMIN_TESTS = "/WEB-INF/view/admin/admin_tests.jsp";
    public static final  String ADMIN_USERS = "/WEB-INF/view/admin/admin_users.jsp";
    public static final String PAGE_FINISH = "/WEB-INF/view/student/page_finish.jsp";
    public static final String MENU = "/WEB-INF/view/menu.jsp";
    public static final String LOGIN_FORM = "/WEB-INF/view/login_form.jsp";
    public static final  String REGISTRATION = "/WEB-INF/view/registration.jsp";
    public static final  String PAGE_BASE_QUESTION = "/WEB-INF/view/student/page_base_question.jsp";
    //====================================================
    protected static final String TEST_ID = "test_id";
    protected static final String QUESTION_ID = "question_id";
    protected static final String ANSWER_ID = "answer_id";
    protected static final String PUBLIC_ID = "public_id";
    protected static final String SERVLET_PATH = "servlet_path";
    protected static final String MESSAGE_SUCCESS = "message_success";

    protected static final  String MESSAGE_BAD_REQUEST = "message_bad_request";
    protected static final String SIZE = "size";
    protected static final String QUESTION = "question";
    protected static final String NUMBER_QUESTION = "number_question";
    protected static final String NAME = "name";
    protected static final String SUBJECT = "subject";
    protected static final  String DIFFICULT = "difficult";
    protected static final  String DURATION = "duration";
    protected static final String CLOUDINARY = "cloudinary";
    protected static final String URL = "url";
    protected static final String WIDTH = "width";
    protected static final String HEIGHT = "height";
    protected static final String WEB_INF = "WEB-INF";
    protected static final String REPEAT_NAME = "repeat_name";
    protected static final String REPEAT_LOGIN = "repeat_login";
    protected static final String REPEAT_PASSWORD = "repeat_password";
    protected static final String PASSWORD = "password";
    protected static final String LOGIN = "login";

    protected static final String ANSWERS = "answers";
    protected static final  String USERS = "users";
    protected static final String QUESTIONS = "questions";
    protected static final String SUBJECTS = "subjects";
    protected static final  String IMAGES = "images";
    protected static final String TESTS = "tests";

    protected static final String TEXT = "text";
    protected static final String RESULT = "result";
    protected static final String TEST = "test";
    protected static final String ROLE = "role";
    protected static final  String ADMIN = "admin";
    protected static final String STUDENT = "student";
    protected static final String USER = "user";
    protected static final String USER_RESULT = "user_result";
    protected static final String RESULT_TEST = "result_test";
    protected static final  String COUNT_PAGES = "count_pages";
    protected static final  String SUB = "sub";
    protected static final String ORDER = "order";
    protected static final String ROWS = "rows";
    protected static final String PAGE = "page";
    protected static final  String STATUS = "status";

    protected static final String LOCALE = "locale";
    protected static final String UA_LANG = "ua";
    protected static final String UA_COUNTRY = "UA";
    protected static final String EN_LANG = "en";
    protected static final String EN_COUNTRY = "UK";
    //==================================
    protected static final String DEFAULT_FILTER_ALL = "all";
    protected static final String DEFAULT_ORDER_NAME_ASC = "name asc";
    protected static final  String DEFAULT_ROWS_5 = "5";
    //================================
    protected static final String ANSWER_WAS_DELETED = "The answer was deleted";
}
