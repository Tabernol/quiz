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

    public List<Test> getAllByAZ(){
        String sql = "select * from test inner join subject on test.subject_id = subject.id order by test.name";
        List<Test> tests;
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            tests = new ArrayList<>();
            Test test;
            while (resultSet.next()) {
                test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setSubjectId(resultSet.getLong("subject_id"));
                test.setName(resultSet.getString("name"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setAmountQuestions(resultSet.getInt("count_question"));
                test.setSubjectName(resultSet.getString("subject.name"));
                tests.add(test);
            }
            return tests;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
