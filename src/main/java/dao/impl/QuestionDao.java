package dao.impl;

import dao.Dao;
import dao.connection.MyDataSource;
import models.Question;
import models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class QuestionDao implements Dao<Question> {
    @Override
    public Question get(Long id) {
//        String sql = "select * from question where id = ?";
//        Question question = new Question();
//        try (Connection con = MyDataSource.getConnection()) {
//            PreparedStatement pst = con.prepareStatement(sql);
//            pst.setLong(1, id);
//            ResultSet resultSet = pst.executeQuery();
//            if (resultSet.next()) {
//                question.setId(resultSet.getLong("id"));
//                question.setText(resultSet.getString("q_text"));
//            }
//            return question;
//        } catch (SQLException e) {
//            throw new RuntimeException(e);
//        }
        return null;
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {
        String sql = "delete from question where id = ?";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    public List<Question> getAll() {
        String sql = "select * from question";
        List<Question> questions = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
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

    public void createQuestion(Long testId, String text) {
        String sql = "insert into question (id, test_id, q_text) values(default, ?, ?)";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(testId));
            pst.setString(2, text);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
