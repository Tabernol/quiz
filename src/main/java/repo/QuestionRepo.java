package repo;

import exeptions.DataBaseException;
import models.Question;

import java.util.List;

public interface QuestionRepo extends BaseRepo<Question>{
    public List<Question> getAllById(Long testId) throws DataBaseException;
    public Question get(Long id) throws DataBaseException;
    public int updateQuestion(String newText, Long id) throws DataBaseException;
    public int updateImageQuestion(String url, Long id) throws DataBaseException;
}
