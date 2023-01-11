package servises;

import exeptions.DataBaseException;
import models.Question;
import repo.QuestionRepo;

import java.util.List;

public class QuestionService {
    QuestionRepo questionRepo;

    public QuestionService(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    public List<Question> getAllById(Long id) throws DataBaseException {
        return questionRepo.getAllById(id);
    }

    public int addQuestion(Long testId, String text) throws DataBaseException {
        return questionRepo.createQuestion(testId, text);
    }

    public int deleteQuestion(Long id) throws DataBaseException {
        return questionRepo.delete(id);
    }

    public Question get(Long id) throws DataBaseException {
        return questionRepo.get(id);
    }

    public int update(String newText, Long id) throws DataBaseException {
        return questionRepo.updateQuestion(newText, id);
    }
}
