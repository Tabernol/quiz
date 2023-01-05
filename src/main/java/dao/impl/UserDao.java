package dao.impl;

import dao.Dao;
import dao.connection.MyDataSource;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User> {
    @Override
    public User get(Long id) {
        String sql = "select * from user where id = ?";
        User user = new User();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, String.valueOf(id));
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getString("role"));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
            }
            resultSet.close();
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Long id) {

    }

    @Override
    public void delete(Long id) {
        String sql = "delete from user where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> getAll() {
        String sql = "select * from user";
        List<User> users;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id"));
                user.setLogin(resultSet.getString("login"));
                user.setPassword(resultSet.getString("password"));
                user.setName(resultSet.getString("name"));
                user.setRole(resultSet.getString("role"));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String login, String password, String name) {
        String sql = "insert into user (id, login, password, role, name) " +
                "values (default, ?, ?, ?, ?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, "student");
            pst.setString(4, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public long getId(String login) {
        String sql = "select * from user where login like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                return resultSet.getLong("id");
            }
            resultSet.close();
        } catch (SQLException e) {
            //log not found login
            throw new RuntimeException(e);
        }
        return -1;
    }


    public void updateUser(Long id, String name, String login, String password, String role, boolean status) {
        String sql = "update user set login = ?, password = ?, role = ?, name = ?, is_blocked = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, role);
            pst.setString(4, name);
            pst.setBoolean(5, status);
            pst.setLong(6, id);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
