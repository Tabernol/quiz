package repo;

import dao.connection.MyDataSource;
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
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
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

    public List<Test> getFilterTest(String subject, String order) {
        String sql = "select * from test where subject like ? order by ?";
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, subject);
            pst.setString(2, order);
            System.out.println("order in repo = " + order);
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
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateInfoTest(Long id, String name, String subject, int difficult, int duration) {
        String sql = "update test set name = ?, subject = ?, difficult = ?, duration = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
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


}
