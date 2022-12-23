package dao.impl;

import dao.Dao;
import dao.connection.MyDataSource;
import models.Subject;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SubjectDao implements Dao<Subject> {
    @Override
    public Subject get(Long id) {
        return null;
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Subject> getAll() {
        String sql = "select * from subject";
        List<Subject> subjects;
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            subjects = new ArrayList<>();
            Subject subject;
            while (resultSet.next()) {
                subject = new Subject();
                subject.setId(resultSet.getInt("id"));
                subject.setName(resultSet.getString("name"));
                subjects.add(subject);
            }
            return subjects;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
