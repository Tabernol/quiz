package servises;

import dao.impl.QuestionDao;
import models.Question;
import repo.QuestionRepo;

import java.util.List;

public class QuestionService {
    QuestionRepo questionRepo = new QuestionRepo();

    public List<Question> getAllById(Long id) {
        return questionRepo.getAllById(id);
    }
}
