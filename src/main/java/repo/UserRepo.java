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

public interface UserRepo extends BaseRepo<User> {

    List<User> getAll() throws DataBaseException;

    boolean isLoginExist(String login) throws DataBaseException;

    long getId(String login) throws DataBaseException;

    int changeStatus(Long id, boolean isBlock) throws DataBaseException;

    List<User> nextPage(String query) throws DataBaseException;

    Integer getCountUsers(String isBlocked) throws DataBaseException;

    String GET_USER_BY_ID = "select * from user where id = ?";

    String GET_ALL = "select * from user";

    String INSERT_NEW_USER = "insert into user (id, login, password, role, name) " +
            "values (default, ?, ?, ?, ?)";

    String IS_LOGIN_UNIQUE = "select * from user where login like ?";

    String UPDATE_USER = "update user set name = ?, role = ? where id = ? ";

    String GET_ID_BY_LOGIN = "select * from user where login like ?";

    String CHANGE_STATUS = "update user set is_blocked = ? where id = ? ";

    String GET_COUNT_USERS_BY_STATUS = "select count(id) from user where is_blocked like ?";

    String LAST_INSERT = "select last_insert_id()";
}
