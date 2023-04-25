package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface AnswerRepo extends BaseRepo<Answer> {
    List<Answer> getAnswersByQuestionId(Long questionId) throws DataBaseException;


}
