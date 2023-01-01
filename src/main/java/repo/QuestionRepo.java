package repo;

import dao.connection.MyDataSource;
import dao.impl.QuestionDao;
import models.Answer;
import models.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepo {
    QuestionDao questionDao = new QuestionDao();
    AnswerRepo answerRepo = new AnswerRepo();

    public List<Question> getAllById(Long id) {
        String sql = "select * from question where test_id = ?";
        List<Question> questions = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, id.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                //return List Answer
                questions.add(question);
            }
            resultSet.close();
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Question get(Long id) {
        String sql = "select * from question where id = ?";
        Question question = new Question();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)){
             pst.setLong(1, id);
             ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                question.setAnswerOptions(answerRepo.getAnswersByQuestionId(id));
            }
            resultSet.close();
            return question;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateQuestion(String newText, Long id) {
        String sql = "update question set q_text = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newText);
            pst.setLong(2, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


}
