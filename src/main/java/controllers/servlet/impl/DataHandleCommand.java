package controllers.servlet.impl;

import command.get.Prg;
import command.post.*;
import controllers.servlet.RequestHandler;
import servises.UserService;

import java.util.HashMap;
import java.util.Map;

public class DataHandleCommand {

    public static final Map<String, RequestHandler> COMMANDS = new HashMap<>();

    static {
        COMMANDS.put("/registration", new Registration());
        COMMANDS.put("/create_test", new CreateTest());
        COMMANDS.put("/delete_test", new DeleteTest());
        COMMANDS.put("/edit_test", new EditTestPost());
        COMMANDS.put("/add_question", new AddQuestion());
        COMMANDS.put("/delete_question", new DeleteQuestion());
        COMMANDS.put("/add_answer", new AddAnswer());
        COMMANDS.put("/edit_question", new EditQuestionPost());
        COMMANDS.put("/delete_answer", new DeleteAnswer());
        COMMANDS.put("/edit_user", new EditUserPost());
        COMMANDS.put("/result_answer", new ResultAnswer());
        COMMANDS.put("/block", new BlockUnblockUser());


    }
}
