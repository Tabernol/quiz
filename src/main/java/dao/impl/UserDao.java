package dao;

import dao.connection.MyDataSource;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDao implements Dao<User>{
    @Override
    public User get(Long id) {
        String sql = "select * from user where id_user = ?";
        User user = new User();
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, String.valueOf(id));
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user.setId(resultSet.getLong("id_user"));
                user.setLogin(resultSet.getString("login_user"));
                user.setPassword(resultSet.getString("pass_user"));
                user.setName(resultSet.getString("name_user"));
                user.setRole(resultSet.getString("role_user"));
            }
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
        String sql = "delete from user where id_user = ?";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
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
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            users = new ArrayList<>();
            User user;
            while (resultSet.next()) {
                user = new User();
                user.setId(resultSet.getLong("id_user"));
                user.setLogin(resultSet.getString("login_user"));
                user.setPassword(resultSet.getString("pass_user"));
                user.setName(resultSet.getString("name_user"));
                user.setRole(resultSet.getString("role_user"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void createUser(String login, String password, String name) {
        String sql = "insert into user (id_user, login_user, pass_user, role_user, name_user) " +
                "values (default, ?, ?, ?, ?)";
        try (Connection con = MyDataSource.getConnection()) {
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, "user");
            pst.setString(4, name);
            pst.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
