package repo;

import connection.MyDataSource;
import dto.ResultDto;
import exeptions.DataBaseException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ResultRepo {
    public List<ResultDto> getPageResultList(String query) throws DataBaseException;
    public List<ResultDto> getAllResult(Long userId) throws DataBaseException;
    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException;
    public Integer getCountResultByUser(Long userId) throws DataBaseException;
    public Integer getCountResultByUserAndSubject(Long userId, String subject) throws DataBaseException;
    public List<String> getDistinctSubject(Long user_id) throws DataBaseException;
}
