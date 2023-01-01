package repo;

import dao.connection.MyDataSource;
import dao.impl.TestDao;
import models.Answer;
import models.Question;
import models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestRepo {

    public List<String> getDistinctSubject() {
        String sql = "select distinct subject from test";
        List<String> subjects;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            subjects = new ArrayList<>();
            while (resultSet.next()) {
                String sub = resultSet.getString("subject");
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Test> getFilterTest(String subject, String order, int rows) {
        String sql = "select * from test where subject like ? order by " + order + " limit ?";
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, subject);
            pst.setInt(2, rows);
            ResultSet resultSet = pst.executeQuery();
            //  pst.setString(2, order);
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInfoTest(Long id, String name, String subject, int difficult, int duration) {
        String sql = "update test set name = ?, subject = ?, difficult = ?, duration = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, subject);
            pst.setString(3, String.valueOf(difficult));
            pst.setString(4, String.valueOf(duration));
            pst.setString(5, String.valueOf(id));
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Integer getCount(String subject) {
        String sql = "select count(subject) from test where subject like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, subject);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1);
            resultSet.close();
            return count;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Integer getCount() {
        String sql = "select count(subject) from test";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Test> getAll(String order, int rows) {
        String sql = "select * from test order by " + order + " limit ?";
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, rows);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public List<Test> nextPage(String subject, String order, Integer rows, Integer numberOfPage) {
        String sql = "select * from test where subject like ? order by " + order + " limit ?,?";
        Integer offSet = (numberOfPage - 1) * rows;
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, subject);
            pst.setInt(2, offSet);
            pst.setInt(3, rows);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Test> nextPage(String order, Integer rows, Integer numberOfPage) {
        String sql = "select * from test order by " + order + " limit ?,?";
        Integer offSet = (numberOfPage - 1) * rows;
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, offSet);
            pst.setInt(2, rows);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public Test getTest(Long id) {
        TestDao testDao = new TestDao();
        QuestionRepo questionRepo = new QuestionRepo();
        AnswerRepo answerRepo = new AnswerRepo();
        Test test = testDao.get(id);
        List<Question> quiz = questionRepo.getAllById(id);
        for (Question question : quiz) {
            List<Answer> answers = answerRepo.getAnswersByQuestionId(question.getId());
            question.setAnswerOptions(answers);
        }
        test.setQuiz(quiz);
        return test;
    }


//    public static void main(String[] args) {
//        MyDataSource.init();
//        TestRepo testRepo = new TestRepo();
//        testRepo.getTest(1L);
//    }


}
