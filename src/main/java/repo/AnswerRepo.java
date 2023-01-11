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
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class AnswerRepo {
    Logger logger = LogManager.getLogger(AnswerRepo.class);

    public List<Answer> getAnswersByQuestionId(Long questionId) throws DataBaseException {
        String sql = "select * from answer where question_id = ?";
        List<Answer> answers = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, questionId.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getLong("id"));
                answer.setQuestionId(resultSet.getLong("question_id"));
                answer.setText(resultSet.getString("a_text"));
                answer.setResult(resultSet.getBoolean("result"));
                answers.add(answer);
            }
            resultSet.close();
            return answers;
        } catch (SQLException e) {
            logger.warn("Can't get orders answer by question id problem");
            throw new DataBaseException("Can't get orders by question id problem" + e.getMessage(), e);
        }
    }


    public int createAnswer(Long questionId, String text, boolean result) throws DataBaseException {
        String sql = "insert into answer (id, question_id, a_text, result) values(default, ?,?,?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, questionId);
            pst.setString(2, text);
            pst.setBoolean(3, result);
            return  pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't insert answer problem");
            throw new DataBaseException("Can't insert answer problem" + e.getMessage(), e);
        }
    }

    public int delete(Long id) throws DataBaseException {
        String sql = "delete from answer where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
           return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can't delete answer problem");
            throw new DataBaseException("Can't delete answer problem" + e.getMessage(), e);
        }
    }
}
