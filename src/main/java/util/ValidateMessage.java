package util;

import command.post.CreateTest;
import exeptions.DataBaseException;
import repo.TestRepo;
import servises.TestService;
import validator.DataValidator;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ValidateMessage {
    Logger logger = Logger.getLogger(ValidateMessage.class.getName());
    TestService testService = new TestService(new TestRepo());

    public boolean isNameExist(String name) {
        boolean isNameExist;
        try {
            isNameExist = testService.isNameExist(name);
        } catch (
                DataBaseException e) {
            logger.log(Level.INFO, "can not creat test");
            throw new RuntimeException(e);
        }
        return isNameExist;
    }

    public String checkFields(String name, String subject, Integer difficult, Integer duration) {
        String message;
        if (isNameExist(name)) {
            message = "this test name already exists";
        } else if (!DataValidator.validateForNamePlusNumber(name)) {
            message = "name must contains only liters and numbers and space from 2-20 items";
        } else if (!DataValidator.validateForName(subject)) {
            message = "subject must contains only liters and space from 2-20 items";
        } else if (!DataValidator.validateDifficult(difficult)) {
            message = "difficult must be from 1 to 100";
        } else if (!DataValidator.validateDuration(duration)) {
            message = "duration must be from 1 to 30 minutes";
        } else {
            message = "All Right";
        }
        return message;
    }


}
