package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.Question;

import repo.impl.QuestionRepoImpl;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call QuestionRepo.class or throw an exception
 */
@Slf4j
public class QuestionService {
    /**
     * Class contains:
     * questionRepo field for work with QuestionRepo.class
     * validatorService field for validate input date from other
     */
    private QuestionRepoImpl questionRepoImpl;
    private ValidatorService validatorService;

    public QuestionService(QuestionRepoImpl questionRepoImpl, ValidatorService validatorService) {
        this.questionRepoImpl = questionRepoImpl;
        this.validatorService = validatorService;
    }

    /**
     * The method takes input questionId and calls to repository layer
     *
     * @param id is unique number Test is database
     * @return List of Question by TestId
     * @throws DataBaseException
     */
    public List<Question> getAllById(Long id) throws DataBaseException {
        log.info("SERVICE QUESTION get all");
        return questionRepoImpl.getAllById(id);
    }

    /**
     * The method takes input, validates it, and calls the repository layer to create a new question
     *
     * @param testId is unique number Test in database
     * @param text   is text of new Question
     * @return 1 if question has added
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int addQuestion(Long testId, String text) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        log.info("SERVICE QUESTION add question for test " + testId);
        return questionRepoImpl.createQuestion(testId, text);
    }

    /**
     * The method takes questionId, and calls the repository layer to delete question
     *
     * @param id is unique number Question in database
     * @return 1 if question has deleted
     * @throws DataBaseException
     */
    public int deleteQuestion(Long id) throws DataBaseException {
        log.info("SERVICE QUESTION deleting question with id " + id);
        return questionRepoImpl.delete(id);
    }

    /**
     * The method takes questionId, and calls the repository layer to get question by id
     *
     * @param id s unique number Question in database
     * @return Question by id
     * @throws DataBaseException
     */
    public Question get(Long id) throws DataBaseException {
        log.info("SERVICE QUESTION get question with id " + id);
        return questionRepoImpl.get(id);
    }

    /**
     * The method takes input, validates it, and calls the repository layer to update this question
     *
     * @param newText is new text of question
     * @param id      is unique number Question in database
     * @return 1 if question has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int update(String newText, Long id) throws DataBaseException, ValidateException {
        validatorService.validateText(newText);
        log.info("SERVICE QUESTION update question with id " + id);
        return questionRepoImpl.updateQuestion(newText, id);
    }

    /**
     * This method calls Question Repo.class to update the URL in the specific question identified by questionId
     *
     * @param url is new URL
     * @param id  is unique number Question in database
     * @return 1 if question has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int updateImage(String url, Long id) throws DataBaseException, ValidateException {
        if (url.equals("")) {
            url = null;
        }
        log.info("SERVICE QUESTION update url image for question with id " + id);
        return questionRepoImpl.updateImageQuestion(url, id);
    }
}
