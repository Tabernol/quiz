package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Question;
import repo.QuestionRepo;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call QuestionRepo.class or throw an exception
 */
public class QuestionService {
    /**
     * Class contains:
     * questionRepo field for work with QuestionRepo.class
     * validatorService field for validate input date from other
     */
    private QuestionRepo questionRepo;
    private ValidatorService validatorService;

    public QuestionService(QuestionRepo questionRepo, ValidatorService validatorService) {
        this.questionRepo = questionRepo;
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
        return questionRepo.getAllById(id);
    }

    /**
     * The method takes input, validates it, and calls the repository layer to create a new question
     * @param testId is unique number Test in database
     * @param text   is text of new Question
     * @return 1 if question has added
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int addQuestion(Long testId, String text) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        return questionRepo.createQuestion(testId, text);
    }

    /**
     * The method takes questionId, and calls the repository layer to delete question
     *
     * @param id is unique number Question in database
     * @return 1 if question has deleted
     * @throws DataBaseException
     */
    public int deleteQuestion(Long id) throws DataBaseException {
        return questionRepo.delete(id);
    }

    /**
     * The method takes questionId, and calls the repository layer to get question by id
     *
     * @param id s unique number Question in database
     * @return Question by id
     * @throws DataBaseException
     */
    public Question get(Long id) throws DataBaseException {
        return questionRepo.get(id);
    }

    /**
     * The method takes input, validates it, and calls the repository layer to update this question
     *
     * @param newText is new text of question
     * @param id is unique number Question in database
     * @return 1 if question has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int update(String newText, Long id) throws DataBaseException, ValidateException {
        validatorService.validateText(newText);
        return questionRepo.updateQuestion(newText, id);
    }

    /**
     * This method calls Question Repo.class to update the URL in the specific question identified by questionId
     * @param url is new URL
     * @param id is unique number Question in database
     * @return 1 if question has updated
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int updateImage(String url, Long id) throws DataBaseException, ValidateException {
        return questionRepo.updateImageQuestion(url, id);
    }
}
