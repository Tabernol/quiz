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

}
