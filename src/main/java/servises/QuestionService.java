package servises;

import dao.impl.QuestionDao;
import models.Question;
import repo.QuestionRepo;

import java.util.List;

public class QuestionService {
    QuestionRepo questionRepo = new QuestionRepo();
    QuestionDao questionDao = new QuestionDao();

    public List<Question> getAllById(Long id) {
        return questionRepo.getAllById(id);
    }

    public void addQuestion(Long testId, String text){
        questionDao.createQuestion(testId,text);
    }

    public void deleteQuestion(Long id){
        questionDao.delete(id);
    }

    public Question get(Long id){
        return questionRepo.get(id);
    }

    public void update(String newText, Long id){
        questionRepo.updateQuestion(newText, id);
    }
}
