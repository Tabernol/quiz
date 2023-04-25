package repo;

import exeptions.DataBaseException;
import models.Question;

import java.util.List;

public interface QuestionRepo extends BaseRepo<Question> {
    List<Question> getAllById(Long testId) throws DataBaseException;

    int updateImageQuestion(String url, Long id) throws DataBaseException;


}
