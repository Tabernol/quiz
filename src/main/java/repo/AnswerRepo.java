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

    String DELETE_ANSWER = "delete from epam_project_testing.answer where id = ?";

    String CREATE_ANSWER = "insert into epam_project_testing.answer " +
            "(id, question_id, a_text, result) values(default, ?,?,?)";
    String GET_ANSWERS_BY_QUESTION_ID = "select * from epam_project_testing.answer where question_id = ?";
}
