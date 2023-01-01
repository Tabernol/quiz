package constans;

public interface PathConst {
    //for all
    String REGISTRATION = "/registration";
    String LOGOUT = "/logout";
    String PROFILE = "/profile";
    String LOGIN_FORM = "/login_form";

    String LANGUAGE = "/Language";


    // for both
    String FILTER_TESTS = "/filter_tests";
    String NEXT_PAGE = "/next_page";
    String HOME = "/home";


    //only student
    String INFO_TEST = "/info_test";
    String START_TEST = "/start_test";
    String NEXT_QUESTION = "/next_question";
    String RESULT_ANSWER = "/result_answer";


    //only admin
    String USERS = "/users";


    String CREATE_TEST = "/create_test";
    String ADD_QUESTION = "/add_question";
    String ADD_ANSWER = "/add_answer";

    String EDIT_USER = "/edit_user";
    String EDIT_TEST = "/edit_test";
    String EDIT_QUESTION = "/edit_question";


    String DELETE_USER = "/delete_user";
    String DELETE_TEST = "/delete_test";
    String DELETE_QUESTION = "/delete_question";
    String DELETE_ANSWER = "/delete_answer";


}
