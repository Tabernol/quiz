package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import lombok.extern.slf4j.Slf4j;
import models.Answer;
import repo.impl.AnswerRepoImpl;

import java.util.List;

/**
 * This class receives data from top-level classes.
 * It checks the input and decides whether to call Answer Repo.class or throw an exception
 */
@Slf4j
public class AnswerService {
    /**
     * Class contains:
     * answerRepo field for work with AnswerRepo.class
     * validatorService field for validate input date from other
     */
    private AnswerRepoImpl answerRepoImpl;
    private ValidatorService validatorService;


    public AnswerService(AnswerRepoImpl answerRepoImpl, ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.answerRepoImpl = answerRepoImpl;
    }

    /**
     * method call repository layer and received list of Answer
     *
     * @param questionId is unique number Question in database
     * @return list of Answer by questionId
     * @throws DataBaseException
     */
    public List<Answer> getAnswers(Long questionId) throws DataBaseException {
        log.info("SERVICE ANSWER get answers by  question "+ questionId);
        return answerRepoImpl.getAnswersByQuestionId(questionId);
    }

    /**
     * Mhe method takes input, validates it, and calls to repository layer to create Answer
     *
     * @param questionId is unique number Question in database
     * @param text       is new text of answer for question by id
     * @param result     can be true or false, and define the result
     * @return 1 if Answer has created
     * @throws DataBaseException
     * @throws ValidateException
     */
    public int createAnswer(Long questionId, String text, boolean result) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        log.info("SERVICE ANSWER creating new answer");
        return answerRepoImpl.createAnswer(questionId, text, result);
    }

    /**
     * The method takes questionId, validates it, and calls to repository layer to delete answer
     *
     * @param id is unique number Answer in database
     * @return 1 if Answer has deleted
     * @throws DataBaseException
     */
    public int deleteAnswer(Long id) throws DataBaseException {
        log.info("SERVICE ANSWER delete answer "+ id);
        return answerRepoImpl.delete(id);
    }

}
