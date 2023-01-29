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

public class TestRepo {
    private static final Logger logger = LogManager.getLogger(TestRepo.class);

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


    public List<Test> getFilterTest(String subject, String order, int rows) throws DataBaseException {
        String sql = "select * from test where subject like ? order by " + order + " limit ?";
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, subject);
            pst.setInt(2, rows);
            ResultSet resultSet = pst.executeQuery();
            //  pst.setString(2, order);
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order filter test");
            throw new DataBaseException("Can not get order filter test" + e.getMessage(), e);
        }
    }

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

    public List<Test> getAll(String order, int rows) throws DataBaseException {
        String sql = "select * from test order by " + order + " limit ?";
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, rows);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order tests");
            throw new DataBaseException("Can not get order tests" + e.getMessage(), e);
        }
    }

    public List<Test> getAll() throws DataBaseException {
        String sql = "select * from test";
        List<Test> tests;
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet resultSet = pst.executeQuery()) {
            tests = new ArrayList<>();
            Test test;
            while (resultSet.next()) {
                test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                tests.add(test);
            }
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order tests");
            throw new DataBaseException("Can not get order tests" + e.getMessage(), e);
        }
    }


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
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order tests in next page");
            throw new DataBaseException("Can not get order tests in next page" + e.getMessage(), e);
        }
    }

    public List<Test> nextPage(String subject, String order, Integer rows, Integer numberOfPage) throws DataBaseException {
        String sql = "select * from test where subject like ? order by " + order + " limit ?,?";
        Integer offSet = (numberOfPage - 1) * rows;
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, subject);
            pst.setInt(2, offSet);
            pst.setInt(3, rows);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order tests in next page");
            throw new DataBaseException("Can not get order tests in next page" + e.getMessage(), e);
        }
    }

    public List<Test> nextPage(String order, Integer rows, Integer numberOfPage) throws DataBaseException {
        String sql = "select * from test order by " + order + " limit ?,?";
        Integer offSet = (numberOfPage - 1) * rows;
        List<Test> tests = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setInt(1, offSet);
            pst.setInt(2, rows);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Test test = new Test();
                test.setId(resultSet.getLong("id"));
                test.setName(resultSet.getString("name"));
                test.setSubject(resultSet.getString("subject"));
                test.setDifficult(resultSet.getInt("difficult"));
                test.setDuration(resultSet.getInt("duration"));
                tests.add(test);
            }
            resultSet.close();
            return tests;
        } catch (SQLException e) {
            logger.warn("Can not get order tests in next page");
            throw new DataBaseException("Can not get order tests in next page" + e.getMessage(), e);
        }
    }

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
            }
            resultSet.close();
            return test;
        } catch (SQLException e) {
            logger.warn("Can not get test");
            throw new DataBaseException("Can not get test" + e.getMessage(), e);
        }
    }

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

    public int createTest(String name, String subject, int difficult, int duration) throws DataBaseException {

        String sql = "insert into test (id, name, subject, difficult, duration) values(default, ?, ?, ?, ?)";
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


}
