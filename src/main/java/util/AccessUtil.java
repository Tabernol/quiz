package util;

import constans.PathConst;

import java.util.HashSet;
import java.util.Set;

public class AccessUtil {

    public static final Set<String> adminAccess = new HashSet<>();
    public static final Set<String> studentAccess = new HashSet<>();
    public static final Set<String> guestAccess = new HashSet<>();

    static {
//        for admin
        adminAccess.add(PathConst.FILTER_TESTS);
        adminAccess.add(PathConst.FILTER_USERS);
        adminAccess.add(PathConst.FILTER_RESULT);

        adminAccess.add(PathConst.LOGIN_FORM);
        adminAccess.add(PathConst.LOGOUT);
        adminAccess.add(PathConst.REGISTRATION);
        adminAccess.add(PathConst.HOME);
        adminAccess.add(PathConst.LANGUAGE);

        adminAccess.add(PathConst.CREATE_TEST);
        adminAccess.add(PathConst.EDIT_TEST);
        adminAccess.add(PathConst.DELETE_TEST);

        adminAccess.add(PathConst.ADD_QUESTION);
        adminAccess.add(PathConst.EDIT_QUESTION);
        adminAccess.add(PathConst.DELETE_QUESTION);

        adminAccess.add(PathConst.ADD_ANSWER);
        adminAccess.add(PathConst.DELETE_ANSWER);

        adminAccess.add(PathConst.EDIT_USER);
        adminAccess.add(PathConst.PROFILE);
        adminAccess.add(PathConst.BLOCK);
        adminAccess.add(PathConst.DOWNLOAD);

        adminAccess.add(PathConst.PRG);
        adminAccess.add(PathConst.UPLOAD_IMAGE);
        adminAccess.add("/filter_images");
        adminAccess.add("/load");
        adminAccess.add("/delete_image");
        adminAccess.add("/update_image");
        adminAccess.add("/remove_image");
        adminAccess.add("/block_test");
//        =====================================================
//        for student
        studentAccess.add(PathConst.REGISTRATION);
        studentAccess.add(PathConst.LOGIN_FORM);
        studentAccess.add(PathConst.LOGOUT);
        studentAccess.add(PathConst.LANGUAGE);

        studentAccess.add(PathConst.PROFILE);
        studentAccess.add(PathConst.HOME);
        studentAccess.add(PathConst.EDIT_USER);

        studentAccess.add(PathConst.FILTER_TESTS);
        studentAccess.add(PathConst.FILTER_RESULT);
        studentAccess.add(PathConst.DOWNLOAD);

        studentAccess.add(PathConst.START_TEST);
        studentAccess.add(PathConst.RESULT_ANSWER);
        studentAccess.add(PathConst.GET_INFO_QUESTION);
        studentAccess.add(PathConst.FINISH_TEST);
        studentAccess.add(PathConst.FINISH);

        studentAccess.add(PathConst.PRG);
//        =======================================
//        for guest
        guestAccess.add(PathConst.LOGIN_FORM);
        guestAccess.add(PathConst.LANGUAGE);
        guestAccess.add(PathConst.REGISTRATION);
    }
}
