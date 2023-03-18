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

public interface UserRepo {
    public User get(Long id) throws DataBaseException;
    public List<User> getAll() throws DataBaseException;
    public Long createUser(String login, String password, String name) throws DataBaseException;
    public boolean isLoginExist(String login) throws DataBaseException;
    public int updateUser(Long id, String name, String role) throws DataBaseException;
    public int updateUser(Long id, String name)
            throws DataBaseException;
    public long getId(String login) throws DataBaseException;
    public int changeStatus(Long id, boolean isBlock) throws DataBaseException;
    public List<User> nextPage(String query) throws DataBaseException;
    public Integer getCountUsers(String isBlocked) throws DataBaseException;
}
