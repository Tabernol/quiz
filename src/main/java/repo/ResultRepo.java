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
    List<ResultDto> getPageResultList(String query) throws DataBaseException;

    List<ResultDto> getAllResult(Long userId) throws DataBaseException;

    Integer addResult(Long userId, Long testId, Integer grade) throws DataBaseException;

    Integer getCountResultByUser(Long userId) throws DataBaseException;

    Integer getCountResultByUserAndSubject(Long userId, String subject) throws DataBaseException;

    List<String> getDistinctSubject(Long user_id) throws DataBaseException;

    String GET_ALL_RESULTS_BY_USER = "select * from test inner join result " +
            "on test.id=result.test_id where user_id = ?";

    String INSERT_RESULT = "insert into result (id, user_id, test_id, grade, time) values(default, ?,?,?,?)";

    String COUNT_RESULT_BY_USER = "select count(grade) from result where user_id = ?";

    String COUNT_RESULT_BY_USER_AND_SUBJECT = "select count(grade) from result inner join test " +
            "on result.test_id=test.id where user_id = ? and subject like ?";

    String DISTINCT_SUBJECTS = "select distinct subject from test" +
            " inner join result on test.id = result.test_id where user_id = ?";
}
