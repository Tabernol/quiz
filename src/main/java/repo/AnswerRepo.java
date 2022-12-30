package repo;

import dao.connection.MyDataSource;
import models.Answer;
import models.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnswerRepo {

    public List<Answer> getAnswersByQuestionId(Long questionId) {
        String sql = "select * from answer where question_id = ?";
        List<Answer> answers = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
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
            return answers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void createAnswer(Long questionId, String text, boolean result) {
        String sql = "insert into answer (id, question_id, a_text, result) values(default, ?,?,?)";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, questionId);
            pst.setString(2, text);
            pst.setBoolean(3, result);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void delete(Long id){
        String sql = "delete from answer where id = ?";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<String> getOptionsAnswerForQuestion(Long idQuestion){
        String sql = "select a_text from answer where question_id = ?";
        List<String> answers = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, idQuestion.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                answers.add(resultSet.getString("a_text"));
            }
            return answers;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
