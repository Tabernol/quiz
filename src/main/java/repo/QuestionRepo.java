package repo;

import dao.connection.MyDataSource;
import dao.impl.QuestionDao;
import models.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionRepo {
    QuestionDao questionDao = new QuestionDao();

    public List<Question> getAllById(Long id) {
        System.out.println("id = " + id);
        String sql = "select * from question where test_id = ?";
        List<Question> questions = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                questions.add(question);
            }
            return questions;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
