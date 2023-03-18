package repo;

import connection.MyDataSource;
import exeptions.DataBaseException;
import models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface TestRepo {
    public List<String> getDistinctSubject() throws DataBaseException;
    public int updateInfoTest(
            Long id,
            String name,
            String subject,
            int difficult,
            int duration) throws DataBaseException;
    public Integer getCount(String subject) throws DataBaseException;
    public Integer getCount() throws DataBaseException;
    public List<Test> nextPage(String query) throws DataBaseException;
    public Test get(Long id) throws DataBaseException;
    public int addPopularity(Long idTest) throws DataBaseException;
    public boolean isNameExist(String name) throws DataBaseException;
    public int delete(Long id) throws DataBaseException;
    public int createTest(String name,
                          String subject,
                          int difficult,
                          int duration) throws DataBaseException;

    public int changeStatus(Long id, Test.Status status) throws DataBaseException;
}
