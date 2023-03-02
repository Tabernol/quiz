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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Class repository has relationship with table Test in MySQL
 *
 * @author MaxKrasnopolskyi
 */
public class TestRepo {
    private static final Logger logger = LogManager.getLogger(TestRepo.class);

    /**
     * method returns a list of the unique subject names of the test(quiz)
     *
     * @return List of String(name Subject)
     * @throws DataBaseException is wrapper of SQLException
     */
    public List<String> getDistinctSubject() throws DataBaseException {
        String sql = "select distinct subject from test";
        List<String> subjects;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            subjects = new ArrayList<>();
            while (resultSet.next()) {
                String sub = resultSet.getString("subject");
                subjects.add(sub);
            }
            return subjects;
        } catch (SQLException e) {
            logger.warn("Can not get order distinct subject from test");
            throw new DataBaseException("Can not get order distinct subject from test" + e.getMessage(), e);
        }
    }

    /**
     * method update field in table 'test' in database
     *
     * @param id        is identification Test in database, table 'test'
     * @param name      is unique new name of test(quiz)
     * @param subject   is new subject of test(quiz)
     * @param difficult is new difficult of test(quiz)
     * @param duration  is new duration of test(quiz)
     * @return 1 if test(quiz) will be updated
     * @throws DataBaseException is wrapper of SQLException
     */
    public int updateInfoTest(Long id, String name, String subject, int difficult, int duration) throws DataBaseException {
        String sql = "update test set name = ?, subject = ?, difficult = ?, duration = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, subject);
            pst.setString(3, String.valueOf(difficult));
            pst.setString(4, String.valueOf(duration));
            pst.setString(5, String.valueOf(id));
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not update test");
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
    public Integer getCount(String subject) throws DataBaseException {
        String sql = "select count(subject) from test where subject like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, subject);
            ResultSet resultSet = pst.executeQuery();
            resultSet.next();
            Integer count = resultSet.getInt(1);
            resultSet.close();
            return count;
        } catch (SQLException e) {
            logger.warn("Can not get count tests of subject");
            throw new DataBaseException("Can not get count tests of subject" + e.getMessage(), e);
        }
    }

    /**
     * method count all tests in database on table 'test'
     *
     * @return count of all test(quiz)
     * @throws DataBaseException is wrapper of SQLException
     */
    public Integer getCount() throws DataBaseException {
        String sql = "select count(subject) from test";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            logger.warn("Can not get count tests");
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
    public List<Test> nextPage(String query) throws DataBaseException {
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setPopularity(resultSet.getInt("popularity"));
                // do not show blocked for student
                test.setStatus(Test.Status.valueOf(resultSet.getString("status").toUpperCase()));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order tests in next page");
            throw new DataBaseException("Can not get order tests in next page" + e.getMessage(), e);
        }
    }

    /**
     * method return Test(quiz) by id
     *
     * @param id is identification Test in database, table 'test'
     * @return test(quiz) by id
     * @throws DataBaseException is wrapper of SQLException
     */
    public Test get(Long id) throws DataBaseException {
        String sql = "select * from test where id = ?";
        Test test = null;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                test.setPopularity(resultSet.getInt("popularity"));
                test.setStatus(Test.Status.valueOf(resultSet.getString("status").toUpperCase()));
            }
            resultSet.close();
            return test;
        } catch (SQLException e) {
            logger.warn("Can not get test");
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
    public int addPopularity(Long idTest) throws DataBaseException {
        String sql = "update test set popularity = popularity + 1 where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, idTest);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not update test");
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
    public boolean isNameExist(String name) throws DataBaseException {
        String sql = "select * from test where name like ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            ResultSet resultSet = pst.executeQuery();
            boolean isExist = resultSet.next();
            resultSet.close();
            return isExist;
        } catch (SQLException e) {
            logger.warn("Can not get order");
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
    public int delete(Long id) throws DataBaseException {
        String sql = "delete from test where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not delete test");
            throw new DataBaseException("Can not delete test" + e.getMessage(), e);
        }
    }

    /**
     * method insert new test(quiz) on table 'test' in database
     *
     * @param name      is unique new name of test(quiz)
     * @param subject   is new subject of test(quiz)
     * @param difficult is new difficult of test(quiz)
     * @param duration  is new duration of test(quiz)
     * @return 1 if test(quiz) has created
     * @throws DataBaseException is wrapper of SQLException
     */
    public int createTest(String name, String subject, int difficult, int duration) throws DataBaseException {
        String sql = "insert into test (id, name, subject, difficult, duration, status) values(default, ?, ?, ?, ?, default)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, name);
            pst.setString(2, subject);
            pst.setInt(3, difficult);
            pst.setInt(4, duration);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not create test");
            throw new DataBaseException("Can not crete test" + e.getMessage(), e);
        }
    }

    /**
     * method change column 'status' in table 'test' in database
     * @param id is identification Test in database, table 'test'
     * @param status is enum in Test.clas can be FREE or BLOCKED
     * @return 1 if test(quiz) has updated
     * @throws DataBaseException
     */
    public int changeStatus(Long id, Test.Status status) throws DataBaseException {
        String sql = "update test set status = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, status.getStatus());
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not change status test with id " + id);
            throw new DataBaseException("Can not update test " + e.getMessage(), e);
        }
    }
}
