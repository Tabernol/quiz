package dao.impl;

import dao.Dao;
import dao.connection.MyDataSource;
import models.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class ResultDao implements Dao<Result> {

    public void addResult(Long userId, Long testId, Integer grade){
        String sql = "insert into result values(default, ?,?,?)";
        try(Connection con = MyDataSource.getConnection();
            PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1,userId);
            pst.setLong(2,testId);
            pst.setInt(3,grade);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public Result get(Long id) {
        return null;
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {

    }

    @Override
    public List<Result> getAll() {
        return null;
    }
}
