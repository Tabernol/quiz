package repo.impl;

import connection.MyDataSource;
import dto.ResultDto;
import exeptions.DataBaseException;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.extern.slf4j.Slf4j;
import repo.ResultRepo;

import javax.sql.DataSource;

/**
 * Class repository has relationship with table Result in MySQL
 *
 * @author MaxKrasnopolskyi
 */
@Slf4j
public class ResultRepoImpl implements ResultRepo {

    private static final String GET_ALL_RESULTS_BY_USER = "select * from test inner join result " +
            "on test.id=result.test_id where user_id = ?";

    private static final String INSERT_RESULT = "insert into result (id, user_id, test_id, grade, date) values(default, ?,?,?,?)";

    private static final String COUNT_RESULT_BY_USER = "select count(grade) from result where user_id = ?";

    private static final String COUNT_RESULT_BY_USER_AND_SUBJECT = "select count(grade) from result inner join test " +
            "on result.test_id=test.id where user_id = ? and subject like ?";

    private static final String DISTINCT_SUBJECTS = "select distinct subject from test" +
            " inner join result on test.id = result.test_id where user_id = ?";
    private final DataSource dataSource;

    public ResultRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method get list of result with some limit, offset, order by, where
     *
     * @param query is ready SQL query. query has an order, limit and offset
     * @return list of ResultDto
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<ResultDto> getPageResultList(String query) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet resultSet = pst.executeQuery();
            List<ResultDto> resultDtoList = new ArrayList<>();
            while (resultSet.next()) {
                resultDtoList.add(buildResultDto(resultSet));
            }
            resultSet.close();
            return resultDtoList;
        } catch (SQLException e) {
            log.warn("Can not get order result list");
            throw new DataBaseException("Can not get order result list" + e.getMessage(), e);
        }
    }

    /**
     * method get List of all result by user id on table 'result'
     *
     * @param userId is identification User in database
     * @return list of result by User id
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<ResultDto> getAllResult(Long userId) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_ALL_RESULTS_BY_USER)) {
            pst.setLong(1, userId);
            ResultSet resultSet = pst.executeQuery();
            List<ResultDto> resultDtoList = new ArrayList<>();
            while (resultSet.next()) {
                resultDtoList.add(buildResultDto(resultSet));
            }
            resultSet.close();
            return resultDtoList;
        } catch (SQLException e) {
            log.warn("Can not get order result list");
            throw new DataBaseException("Can not get order result list" + e.getMessage(), e);

        }
    }

    private ResultDto buildResultDto(ResultSet resultSet) throws SQLException {
        return ResultDto.builder()
                .testName(resultSet.getString("name"))
                .subject(resultSet.getString("subject"))
                .difficult(resultSet.getInt("difficult"))
                .duration(resultSet.getInt("duration"))
                .grade(resultSet.getInt("grade"))
                .date(resultSet.getTimestamp("date").toLocalDateTime().toLocalDate()).build();
    }

    /**
     * method insert result on table 'result' in database
     *
     * @param userId is identification User in database
     * @param testId is identification Test(quiz) in database
     * @param grade  is grade of test(quiz)
     * @return 1 if data has inserted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(INSERT_RESULT)) {
            pst.setLong(1, userId);
            pst.setLong(2, testId);
            pst.setInt(3, grade);
            pst.setTimestamp(4, Timestamp.valueOf(LocalDateTime.now()));
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not insert into result");
            throw new DataBaseException("Can not insert into result" + e.getMessage(), e);
        }
    }

    /**
     * method count how many grade has User by id
     *
     * @param userId is identification User in database
     * @return count of result
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCountResultByUser(Long userId) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(COUNT_RESULT_BY_USER)) {
            pst.setLong(1, userId);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1);
            resultSet.close();
            return count;
        } catch (SQLException e) {
            log.warn("Can not get count from table result with id = " + userId);
            throw new DataBaseException("Can not get count from table result" + e.getMessage(), e);
        }
    }

    /**
     * method count how many grade has User by id and subjectId
     *
     * @param userId  is identification User in database
     * @param subject is name on table 'subject'
     * @return count of result
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCountResultByUserAndSubject(Long userId, String subject) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(COUNT_RESULT_BY_USER_AND_SUBJECT)) {
            pst.setLong(1, userId);
            pst.setString(2, subject);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1);
            resultSet.close();
            return count;
        } catch (SQLException e) {
            log.warn("Can not get count from table result with id = " + userId);
            throw new DataBaseException("Can not get count from table result" + e.getMessage(), e);
        }
    }

    /**
     * method returns a list of the unique subject names
     * of the test(quiz) items that the user has completed
     *
     * @return List of String(name Subject)
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<String> getDistinctSubject(Long userId) throws DataBaseException {
        List<String> subjects;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DISTINCT_SUBJECTS)) {
            pst.setLong(1, userId);
            ResultSet resultSet = pst.executeQuery();
            subjects = new ArrayList<>();
            while (resultSet.next()) {
                String sub = resultSet.getString("subject");
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException e) {
            log.warn("Can not get order distinct subject from test");
            throw new DataBaseException("Can not get order distinct subject from test" + e.getMessage(), e);
        }
    }
}
