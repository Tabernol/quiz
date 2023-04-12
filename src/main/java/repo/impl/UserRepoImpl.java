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
        User user = null;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_USER_BY_ID)) {
            pst.setString(1, String.valueOf(id));
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                user = buildUser(resultSet);
            }
            resultSet.close();
            return user;
        } catch (SQLException e) {
            log.warn("Can not get order user");
            throw new DataBaseException("Can not get order user " + e.getMessage(), e);
        }
    }

    /**
     * method return List of all User from database
     *
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<User> getAll() throws DataBaseException {
        List<User> users = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_ALL);
             ResultSet resultSet = pst.executeQuery()) {
            while (resultSet.next()) {
                users.add(buildUser(resultSet));
            }
            return users;
        } catch (SQLException e) {
            log.warn("Can not get order all user");
            throw new DataBaseException("Can not get order all user " + e.getMessage(), e);
        }
    }

    /**
     * Constructs a new User object using the data stored in the given ResultSet.
     *
     * @param resultSet the ResultSet containing the user data
     * @return a new User object with the data from the ResultSet
     * @throws SQLException if there is an error accessing the ResultSet data
     */
    private User buildUser(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getLong("id"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));
        user.setName(resultSet.getString("name"));
        user.setRole(User.Role.valueOf(resultSet.getString("role").toUpperCase()));
        user.setBlocked(resultSet.getBoolean("is_blocked"));
        return user;
    }

    /**
     * method create new User
     * return 1 when User created and insert into database
     * or throws DataBaseException
     *
     * @param user Insert new user to database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public long create(User user) throws DataBaseException {
        Connection con = null;
        PreparedStatement pst = null;
        ResultSet resultSet = null;
        try {
            con = MyDataSource.getConnection();
            con.setAutoCommit(false);
            pst = con.prepareStatement(INSERT_NEW_USER);
            pst.setString(1, user.getLogin());
            pst.setString(2, user.getPassword());
            pst.setString(3, User.Role.STUDENT.getRole());
            pst.setString(4, user.getName());
            pst.executeUpdate();

            pst = con.prepareStatement(LAST_INSERT);
            resultSet = pst.executeQuery();
            resultSet.next();
            Long userId = resultSet.getLong("last_insert_id()");
            con.commit();
            return userId;
        } catch (SQLException e) {
            try {
                con.rollback();
            } catch (SQLException ex) {
                log.warn("Catch exception at rollback");
                throw new DataBaseException("Can not create user " + e.getMessage(), e);
            }
            log.warn("Can not create user");
            throw new DataBaseException("Can not create user " + e.getMessage(), e);
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
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(IS_LOGIN_UNIQUE)) {
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            return result;
        } catch (SQLException e) {
            log.warn("Can not get id by login");
            throw new DataBaseException("Can not get id by login " + e.getMessage(), e);
        }
    }

    /**
     * method update User in database
     * Can update only name and role
     * <p>
     * return 1 if user update
     *
     * @param user with new information about user in database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int update(User user) throws DataBaseException {
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_USER)) {
            pst.setString(1, user.getName());
            pst.setString(2, user.getRole().getRole());
            pst.setLong(3, user.getId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not update user");
            throw new DataBaseException("Can not update user " + e.getMessage(), e);
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
        long result = -1;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_ID_BY_LOGIN)) {
            pst.setString(1, login);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                result = resultSet.getLong("id");
            }
            resultSet.close();
            return result;
        } catch (SQLException e) {
            log.warn("Can not get id by login");
            throw new DataBaseException("Can not get id by login " + e.getMessage(), e);
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
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CHANGE_STATUS)) {
            pst.setBoolean(1, isBlock);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not change status user with id {}", id);
            throw new DataBaseException("Can not update user " + e.getMessage(), e);
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
                users.add(buildUser(resultSet));
            }
            resultSet.close();
            return users;
        } catch (SQLException e) {
            log.warn("Can not get order users in next page");
            throw new DataBaseException("Can not get order users in next page " + e.getMessage(), e);
        }
    }

    /**
     * method returns the number of users with the selected status from the database
     *
     * @param isBlocked can be  'BLOCKED' 'UNBLOCKED' or 'ALL'
     * @return count of users with choose filter
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCountUsers(String isBlocked) throws DataBaseException {
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_COUNT_USERS_BY_STATUS)) {
            pst.setString(1, isBlocked);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            int count = resultSet.getInt(1);
            resultSet.close();
            return count;

        } catch (SQLException e) {
            log.warn("Can not get count users");
            throw new DataBaseException("Can not get count users " + e.getMessage(), e);
        }
    }

    /**
     *
     * currently not using
     * @param id the ID of the record to delete
     * @return the number of records deleted from the database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(Long id) throws DataBaseException {
        return 0;
    }
}
