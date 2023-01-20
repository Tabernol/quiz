package servises;

import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Question;
import repo.QuestionRepo;

import java.util.List;

public class QuestionService {
    private QuestionRepo questionRepo;
   private ValidatorService validatorService;

    public QuestionService(QuestionRepo questionRepo, ValidatorService validatorService) {
        this.questionRepo = questionRepo;
        this.validatorService = validatorService;
    }

    public List<Question> getAllById(Long id) throws DataBaseException {
        return questionRepo.getAllById(id);
    }

    public int addQuestion(Long testId, String text) throws DataBaseException, ValidateException {
        validatorService.validateText(text);
        return questionRepo.createQuestion(testId, text);
    }

    public int deleteQuestion(Long id) throws DataBaseException {
        return questionRepo.delete(id);
    }

    public Question get(Long id) throws DataBaseException {
        return questionRepo.get(id);
    }

    public int update(String newText, Long id) throws DataBaseException, ValidateException {
        validatorService.validateText(newText);
        return questionRepo.updateQuestion(newText, id);
    }
}
