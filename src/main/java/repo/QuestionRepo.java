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
import java.util.logging.Level;
import java.util.logging.Logger;

public class QuestionRepo {
    Logger logger = Logger.getLogger(QuestionRepo.class.getName());

    public List<Question> getAllById(Long testId) throws DataBaseException {
        String sql = "select * from question where test_id = ?";
        List<Question> questions = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, testId.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                questions.add(question);
            }
            resultSet.close();
            return questions;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Can't get order question by test id");
            throw new DataBaseException("Can't get order question by test id" + e.getMessage(), e);

        }
    }

    public Question get(Long id) throws DataBaseException {
        String sql = "select * from question where id = ?";
        Question question = new Question();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                // question.setAnswerOptions(answerRepo.getAnswersByQuestionId(id));
            }
            resultSet.close();
            return question;
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Can't get question by id");
            throw new DataBaseException("Can't get question by id" + e.getMessage(), e);
        }
    }

    public int updateQuestion(String newText, Long id) throws DataBaseException {
        String sql = "update question set q_text = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newText);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Can' update question by id");
            throw new DataBaseException("Can' update question by id" + e.getMessage(), e);
        }
    }

    public void delete(Long id) throws DataBaseException {
        String sql = "delete from question where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            pst.executeUpdate();

        } catch (SQLException e) {
            logger.log(Level.WARNING, "Can' delete question by id");
            throw new DataBaseException("Can' delete question by id" + e.getMessage(), e);
        }
    }

    public int createQuestion(Long testId, String text) throws DataBaseException {
        String sql = "insert into question (id, test_id, q_text) values(default, ?, ?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, String.valueOf(testId));
            pst.setString(2, text);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.log(Level.WARNING, "Can not create question by id");
            throw new DataBaseException("Can not create question by id" + e.getMessage(), e);
        }
    }
}
