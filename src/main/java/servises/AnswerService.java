package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Answer;
import repo.AnswerRepo;
import validator.DataValidator;

import java.sql.SQLException;
import java.util.List;

/**
 *
 */
public class AnswerService {
    /**
     * Class contains:
     * answerRepo field for work with AnswerRepo.class
     * validatorService field for validate input date from other
     */
    private AnswerRepo answerRepo;
    private ValidatorService validatorService;



    public AnswerService(AnswerRepo answerRepo, ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.answerRepo = answerRepo;
    }

    /**
     * method call repository layer and received list of Answer
     * @param questionId is unique number Question in database
     * @return list of Answer by questionId
     * @throws DataBaseException
     */
    public List<Answer> getAnswers(Long questionId) throws DataBaseException {
        return answerRepo.getAnswersByQuestionId(questionId);
    }

    /**
     * Mhe method takes input, validates it, and calls to repository layer to create Answer
     * @param questionId is unique number Question in database
     * @param text is new text of answer for question by id
     * @param result can be true or false, and define the result
     * @return 1 if Answer has created
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int createAnswer(Long questionId, String text, boolean result) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        return answerRepo.createAnswer(questionId, text, result);
    }

    /**
     * The method takes questionId, validates it, and calls to repository layer to delete answer
     * @param id is unique number Answer in database
     * @return 1 if Answer has deleted
     * @throws DataBaseException
     */
    public int deleteAnswer(Long id) throws DataBaseException {
        return answerRepo.delete(id);
    }

}
