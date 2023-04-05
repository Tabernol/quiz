package repo.impl;

import connection.MyDataSource;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import repo.UserRepo;

/**
 * Class repository has relationship with table User in MySQL
 *
 * @author MaxKrasnopolskyi
 */
@Slf4j
public class UserRepoImpl implements UserRepo {

    /**
     * method return User from database by ID
     * if ID does not exist return empty User
     *
     * @param id is identification User in database, it generated by database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
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
                user.setRole(User.Role.valueOf(resultSet.getString("role").toUpperCase()));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
            }
            resultSet.close();
            return user;
        } catch (SQLException e) {
            log.warn("Can not get order user");
            throw new DataBaseException("Can not get order user" + e.getMessage(), e);
        }
    }

    /**
     * method return List of all User from database
     *
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
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
                user.setRole(User.Role.valueOf(resultSet.getString("role").toUpperCase()));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            log.warn("Can not get order all user");
            throw new DataBaseException("Can not get order all user" + e.getMessage(), e);
        }
    }

    /**
     * method create new User
     * return 1 when User created and insert into database
     * or throws DataBaseException
     *
     * @param login    is email input at registration and have to unique
     * @param password is password input at registration
     * @param name     is name of User
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Long createUser(String login, String password, String name) throws DataBaseException {
        String sql = "insert into user (id, login, password, role, name) " +
                "values (default, ?, ?, ?, ?)";
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            con = MyDataSource.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(sql);
            pst.setString(1, login);
            pst.setString(2, password);
            pst.setString(3, User.Role.STUDENT.getRole());
            pst.setString(4, name);
            pst.executeUpdate();

            pst = con.prepareStatement("select last_insert_id()");
            resultSet = pst.executeQuery();
            resultSet.next();
            Long userId = resultSet.getLong("last_insert_id()");
            con.commit();
            System.out.println("userId = " + userId);
            return userId;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.warn("Catch exception at rollback");
                throw new DataBaseException("Can not create user" + e.getMessage(), e);
            }
            log.warn("Can not create user");
            throw new DataBaseException("Can not create user" + e.getMessage(), e);
        } finally {
            try {
                resultSet.close();
                pst.close();
                con.close();
            } catch (SQLException e) {
                log.warn("Problem in finally block during create user");
                throw new DataBaseException("Can not create user" + e.getMessage(), e);
            }
        }
    }

    /**
     * method check exist this login in database
     * <p>
     * return true if login exist in database
     * login is unique
     *
     * @param login is email input at authorization and registration
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public boolean isLoginExist(String login) throws DataBaseException {
        String sql = "select * from user where login like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            log.warn("Can not get id by login");
            throw new DataBaseException("Can not get id by login" + e.getMessage(), e);
        }
    }

    /**
     * method update User in database
     * Can update only name and role
     * <p>
     * return 1 if user update
     *
     * @param id   is identification User in database, it generated by database
     * @param name is new value for update User
     * @param role is new role for User
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int updateUser(Long id, String name, String role) throws DataBaseException {
        String sql = "update user set name = ?, role = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, role);
            pst.setLong(3, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not update user");
            throw new DataBaseException("Can not update user" + e.getMessage(), e);
        }
    }


    /**
     * method update User in database
     * Can update only name
     * <p>
     * return 1 if user update
     *
     * @param id   is identification User in database, it generated by database
     * @param name is new value for update User
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int updateUser(Long id, String name)
            throws DataBaseException {
        String sql = "update user set name = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not update user");
            throw new DataBaseException("Can not update user" + e.getMessage(), e);
        }
    }


    /**
     * method return identification User in database, it generated by database
     *
     * @param login is email input at authorization and registration
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public long getId(String login) throws DataBaseException {
        String sql = "select * from user where login like ?";
        long result = -1;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong("id");
            }
            resultSet.close();
            return result;
        } catch (SQLException e) {
            log.warn("Can not get id by login");
            throw new DataBaseException("Can not get id by login" + e.getMessage(), e);
        }
    }

    /**
     * method change field status in database
     * it helps in block/unblock user in app
     *
     * @param id      is identification User in database, it generated by database
     * @param isBlock new status in database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int changeStatus(Long id, boolean isBlock) throws DataBaseException {
        String sql = "update user set is_blocked = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setBoolean(1, isBlock);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not change status user with id " + id);
            throw new DataBaseException("Can not update user" + e.getMessage(), e);
        }
    }

    /**
     * method return list of users with some limit, offset, order by,  and filter
     *
     * @param query is ready SQL query. query has an order, limit and offset
     * @return list of user
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<User> nextPage(String query) throws DataBaseException {
        List<User> users = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                User user = new User();
                user.setName(resultSet.getString("name"));
                user.setLogin(resultSet.getString("login"));
                user.setBlocked(resultSet.getBoolean("is_blocked"));
                user.setId(resultSet.getLong("id"));
                user.setRole(User.Role.valueOf(resultSet.getString("role").toUpperCase()));
                users.add(user);
            }
            resultSet.close();
            return users;
        } catch (SQLException e) {
            log.warn("Can not get order users in next page");
            throw new DataBaseException("Can not get order users in next page" + e.getMessage(), e);
        }
    }

    /**
     * method returns the number of users with the selected status from the database
     * @param isBlocked can be  'BLOCKED' 'UNBLOCKED' or 'ALL'
     * @return count of users with choose filter
     * @throws DataBaseException
     */
    @Override
    public Integer getCountUsers(String isBlocked) throws DataBaseException {
        String sql = "select count(id) from user where is_blocked like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, isBlocked);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            return count;

        } catch (SQLException e) {
            log.warn("Can not get count users");
            throw new DataBaseException("Can not get count users" + e.getMessage(), e);
        }
    }
}