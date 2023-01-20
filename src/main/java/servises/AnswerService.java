package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Answer;
import repo.AnswerRepo;

import java.sql.SQLException;
import java.util.List;

public class AnswerService {
    private AnswerRepo answerRepo;
    private ValidatorService validatorService;

    public AnswerService(AnswerRepo answerRepo, ValidatorService validatorService) {
        this.validatorService = validatorService;
        this.answerRepo = answerRepo;
    }

    public List<Answer> getAnswers(Long questionId) throws DataBaseException {
        return answerRepo.getAnswersByQuestionId(questionId);
    }

    public int createAnswer(Long questionId, String text, boolean result) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        return answerRepo.createAnswer(questionId, text, result);
    }

    public int deleteAnswer(Long id) throws DataBaseException {
        return answerRepo.delete(id);
    }

}
