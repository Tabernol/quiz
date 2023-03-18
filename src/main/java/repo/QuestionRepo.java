package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface QuestionRepo {
    public List<Question> getAllById(Long testId) throws DataBaseException;
    public Question get(Long id) throws DataBaseException;
    public int updateQuestion(String newText, Long id) throws DataBaseException;
    public int delete(Long id) throws DataBaseException;
    public int createQuestion(Long testId, String text) throws DataBaseException;
    public int updateImageQuestion(String url, Long id) throws DataBaseException;
}
