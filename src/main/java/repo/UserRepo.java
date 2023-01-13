package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserRepo {
    Logger logger = LogManager.getLogger(UserRepo.class.getName());

    public User get(Long id) throws DataBaseException {
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
            logger.warn("Can not get order user");
            throw new DataBaseException("Can not get order user" + e.getMessage(), e);
        }
    }

    public int delete(Long id) throws DataBaseException {
        String sql = "delete from user where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not delete user");
            throw new DataBaseException("Can not delete user" + e.getMessage(), e);
        }
    }

    public List<User> getAll() throws DataBaseException {
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
            logger.warn("Can not get order all user");
            throw new DataBaseException("Can not get order all user" + e.getMessage(), e);
        }
    }

    public int createUser(String login, String password, String name) throws DataBaseException {
        String sql = "insert into user (id, login, password, role, name) " +
                "values (default, ?, ?, ?, ?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, "student");
            pst.setString(4, name);
            return pst.executeUpdate();
            //   return row;
        } catch (SQLException e) {
            logger.warn("Can not create user");
            throw new DataBaseException("Can not create user" + e.getMessage(), e);
        }
    }

    public long getId(String login) throws DataBaseException {
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
            logger.warn("Can not get id by login");
            throw new DataBaseException("Can not get id by login" + e.getMessage(), e);
        }
        return -1;
    }


    public int updateUser(Long id, String name, String role, boolean status)
            throws DataBaseException {
        String sql = "update user set role = ?, name = ?, is_blocked = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, role);
            pst.setString(2, name);
            pst.setBoolean(3, status);
            pst.setLong(4, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not update user");
            throw new DataBaseException("Can not update user" + e.getMessage(), e);
        }
    }

    public int updateUser(Long id, String name)
            throws DataBaseException {
        String sql = "update user set name = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not update user");
            throw new DataBaseException("Can not update user" + e.getMessage(), e);
        }
    }
}
