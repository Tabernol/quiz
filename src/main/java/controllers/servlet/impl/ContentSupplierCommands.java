package controllers.servlet.impl;

import command.*;
import command.post.DeleteUser;
import controllers.servlet.RequestHandler;

import java.util.HashMap;
import java.util.Map;

public class ContentSupplierCommands {
    public static final Map<String, RequestHandler> COMMANDS = new HashMap<>();


    static {
        COMMANDS.put("/users", new AllUser());
        COMMANDS.put("/registration", new Registration());
        COMMANDS.put("/login_form", new LoginForm());
        COMMANDS.put("/home", new Home());
        COMMANDS.put("/logout", new Logout());
        COMMANDS.put("/edit_user", new EditUser());
        COMMANDS.put("/delete_user", new DeleteUser());
        COMMANDS.put("/filter_tests", new FilterTests());
        COMMANDS.put("/edit_test", new EditTest());
        COMMANDS.put("/edit_question", new EditQuestion());
        COMMANDS.put("/profile", new Profile());
        COMMANDS.put("/next_page", new NextPage());
        COMMANDS.put("/info_test", new PageTest());
        COMMANDS.put("/start_test", new StartTest());
        COMMANDS.put("/next_question", new NextQuestion());
        COMMANDS.put("/prg_edit_question_servlet", new PrgEditQuestionServlet());
        COMMANDS.put("/prg_edit_test_servlet", new PrgEditTestServlet());
        COMMANDS.put("/prg_create_test", new PrgCreateTest());
        COMMANDS.put("/to_create_test", new ToCreateTest());
        COMMANDS.put("/edit_profile", new EditProfile());

    }


}
