package constans;

public interface PathConst {
    //for all
    String REGISTRATION = "/registration";
    String LOGIN_FORM = "/login_form";
    String LANGUAGE = "/language";


    // for both
    String LOGIN = "/login";
    String LOGOUT = "/logout";
    String PROFILE = "/profile";
    String FILTER_TESTS = "/filter_tests";

    String FILTER_RESULT = "/filter_result";

    String DOWNLOAD = "/download";
    String HOME = "/home";

    String PRG = "/prg";


    //only student
    String INFO_TEST = "/info_test";
    String START_TEST = "/start_test";
    //    String NEXT_QUESTION = "/next_question";
    String RESULT_ANSWER = "/result_answer";
    String GET_INFO_QUESTION = "/get_text_question";
    String FINISH = "/finish";
    String FINISH_TEST = "/finish_test";


    //only admin
    String FILTER_USERS = "/filter_users";
    String BLOCK = "/block";


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

   // String UPLOAD_IMAGE = "/upload_image";
    String FILTER_IMAGES = "/filter_images";
    String LOAD = "/load";
    String DELETE_IMAGE = "/delete_image";
    String UPDATE_IMAGE = "/update_image";
    String REMOVE_IMAGE = "/remove_image";
    String BLOCK_TEST = "/block_test";


}
