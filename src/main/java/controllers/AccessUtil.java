package controllers;



import java.util.HashSet;
import java.util.Set;

import static controllers.PathConst.*;


public class AccessUtil {

    public static final Set<String> adminAccess = new HashSet<>();
    public static final Set<String> studentAccess = new HashSet<>();
    public static final Set<String> guestAccess = new HashSet<>();

    static {
//        for admin
        adminAccess.add(FILTER_TESTS);
        adminAccess.add(FILTER_USERS);
        adminAccess.add(FILTER_RESULT);

        adminAccess.add(LOGIN_FORM);
        adminAccess.add(LOGOUT);
        adminAccess.add(REGISTRATION);
        adminAccess.add(HOME);
        adminAccess.add(LANGUAGE);

        adminAccess.add(CREATE_TEST);
        adminAccess.add(EDIT_TEST);
        adminAccess.add(DELETE_TEST);

        adminAccess.add(ADD_QUESTION);
        adminAccess.add(EDIT_QUESTION);
        adminAccess.add(DELETE_QUESTION);

        adminAccess.add(ADD_ANSWER);
        adminAccess.add(DELETE_ANSWER);

        adminAccess.add(EDIT_USER);
        adminAccess.add(PROFILE);
        adminAccess.add(BLOCK);
        adminAccess.add(DOWNLOAD);

        adminAccess.add(PRG);
        adminAccess.add(FILTER_IMAGES);
        adminAccess.add(LOAD);
        adminAccess.add(DELETE_IMAGE);
        adminAccess.add(UPDATE_IMAGE);
        adminAccess.add(BLOCK_TEST);
        adminAccess.add(LOGIN);
//        =====================================================
//        for student
        studentAccess.add(REGISTRATION);
        studentAccess.add(LOGIN_FORM);
        studentAccess.add(LOGOUT);
        studentAccess.add(LANGUAGE);

        studentAccess.add(PROFILE);
        studentAccess.add(HOME);
        studentAccess.add(EDIT_USER);

        studentAccess.add(FILTER_TESTS);
        studentAccess.add(FILTER_RESULT);
        studentAccess.add(DOWNLOAD);

        studentAccess.add(START_TEST);
        studentAccess.add(RESULT_ANSWER);
        studentAccess.add(GET_INFO_QUESTION);
        studentAccess.add(FINISH_TEST);
        studentAccess.add(FINISH);

        studentAccess.add(PRG);
        studentAccess.add(LOGIN);
//        =======================================
//        for guest
        guestAccess.add(LOGIN_FORM);
        guestAccess.add(LANGUAGE);
        guestAccess.add(REGISTRATION);
    }
}
