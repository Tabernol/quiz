package repo.impl;

import connection.MyDataSource;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repo.TestRepo;

import javax.sql.DataSource;

/**
 * Class repository has relationship with table Test in MySQL
 *
 * @author MaxKrasnopolskyi
 */
@Slf4j
public class TestRepoImpl implements TestRepo {
    private final DataSource dataSource;

    public TestRepoImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * method returns a list of the unique subject names of the test(quiz)
     *
     * @return List of String(name Subject)
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<String> getDistinctSubject() throws DataBaseException {
        List<String> subjects;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_DISTINCT_SUBJECT);
             ResultSet resultSet = pst.executeQuery()) {
            subjects = new ArrayList<>();
            while (resultSet.next()) {
                String sub = resultSet.getString(SUBJECT);
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException e) {
            log.warn("Can not get order distinct subject from test");
            throw new DataBaseException("Can not get order distinct subject from test" + e.getMessage(), e);
        }
    }

    /**
     * method update field in table 'test' in database
     *
     * @param test where:
     *             id        is identification Test in database, table 'test'
     *             name      is unique new name of test(quiz)
     *             subject   is new subject of test(quiz)
     *             difficult is new difficult of test(quiz)
     *             duration  is new duration of test(quiz)
     * @return 1 if test(quiz) will be updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int update(Test test) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(UPDATE_TEST)) {
            pst.setString(1, test.getName());
            pst.setString(2, test.getSubject());
            pst.setInt(3, test.getDifficult());
            pst.setInt(4, test.getDuration());
            pst.setLong(5, test.getId());
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not update test");
            throw new DataBaseException("Can not update test" + e.getMessage(), e);
        }
    }

    /**
     * method count how many test with the same name in table 'test'
     *
     * @param subject is name of tests(quiz)
     * @return count of test with the same name
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCount(String subject) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(COUNT_BY_SUBJECT)) {
            pst.setString(1, subject);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1);
            resultSet.close();
            return count;
        } catch (SQLException e) {
            log.warn("Can not get count tests of subject");
            throw new DataBaseException("Can not get count tests of subject" + e.getMessage(), e);
        }
    }

    /**
     * method count all tests in database on table 'test'
     *
     * @return count of all test(quiz)
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCount() throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(COUNT_OF_SUBJECTS);
             ResultSet resultSet = pst.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            log.warn("Can not get count tests");
            throw new DataBaseException("Can not get count tests" + e.getMessage(), e);
        }
    }

    /**
     * method return list of test(quiz) with some limit, offset, order by and filter
     *
     * @param query is ready SQL query. query has an order, limit and offset
     * @return list of test(quiz)
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<Test> nextPage(String query) throws DataBaseException {
        List<Test> tests = new ArrayList<>();
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                tests.add(buildTest(resultSet));
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            log.warn("Can not get order tests in next page");
            throw new DataBaseException("Can not get order tests in next page" + e.getMessage(), e);
        }
    }

    private Test buildTest(ResultSet resultSet) throws SQLException {
        Test test = new Test();
        test.setId(resultSet.getLong("id"));
        test.setName(resultSet.getString("name"));
        test.setSubject(resultSet.getString("subject"));
        test.setDifficult(resultSet.getInt("difficult"));
        test.setDuration(resultSet.getInt("duration"));
        test.setPopularity(resultSet.getInt("popularity"));
        test.setStatus(Test.Status.valueOf(resultSet.getString("status").toUpperCase()));
        return test;
    }

    /**
     * method return Test(quiz) by id
     *
     * @param id is identification Test in database, table 'test'
     * @return test(quiz) by id
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Test get(Long id) throws DataBaseException {
        Test test = null;
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_TEST)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                test = buildTest(resultSet);
            }
            resultSet.close();
            return test;
        } catch (SQLException e) {
            log.warn("Can not get test");
            throw new DataBaseException("Can not get test" + e.getMessage(), e);
        }
    }

    /**
     * method add +1 to field popularity on table 'test'
     *
     * @param idTest is identification Test in database, table 'test'
     * @return 1 if field will be updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int addPopularity(Long idTest) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(ADD_POINT_POPULARITY)) {
            pst.setLong(1, idTest);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not update test");
            throw new DataBaseException("Can not update test" + e.getMessage(), e);

        }
    }

    /**
     * method check exist this name test(quiz) on table 'test' in database
     *
     * @param name is unique name on table 'test' in database
     * @return true if name exist and false if this name no exist
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public boolean isNameExist(String name) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(IS_TEST_EXIST)) {
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            boolean isExist = resultSet.next();
            resultSet.close();
            return isExist;
        } catch (SQLException e) {
            log.warn("Can not get order");
            throw new DataBaseException("Can not get order" + e.getMessage(), e);
        }
    }

    /**
     * method delete test from database
     *
     * @param id is identification Test in database, table 'test'
     * @return 1 if test(quiz) will be deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(Long id) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_TEST)) {
            pst.setLong(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not delete test");
            throw new DataBaseException("Can not delete test" + e.getMessage(), e);
        }
    }

    /**
     * method insert new test(quiz) on table 'test' in database
     *
     * @param test where:
     *             name      is unique new name of test(quiz)
     *             subject   is new subject of test(quiz)
     *             difficult is new difficult of test(quiz)
     *             duration  is new duration of test(quiz)
     * @return 1 if test(quiz) has created
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public long create(Test test) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_TEST)) {
            pst.setString(1, test.getName());
            pst.setString(2, test.getSubject());
            pst.setInt(3, test.getDifficult());
            pst.setInt(4, test.getDuration());
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not create test");
            throw new DataBaseException("Can not crete test" + e.getMessage(), e);
        }
    }

    /**
     * method change column 'status' in table 'test' in database
     *
     * @param id     is identification Test in database, table 'test'
     * @param status is enum in Test.clas can be FREE or BLOCKED
     * @return 1 if test(quiz) has updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int changeStatus(Long id, Test.Status status) throws DataBaseException {
        try (Connection con = dataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CHANGE_STATUS_TEST)) {
            pst.setString(1, status.getStatus());
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not change status test with id " + id);
            throw new DataBaseException("Can not update test " + e.getMessage(), e);
        }
    }
}
