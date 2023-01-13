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

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class ResultRepo {
    Logger logger = LogManager.getLogger(ResultRepo.class);

    public List<ResultDto> resultDtoList(Long userId) throws DataBaseException {
        String sql = "select name, subject, difficult, duration, grade from test" +
                " inner join result on test.id=result.test_id where user_id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, userId);
            ResultSet resultSet = pst.executeQuery();
            List<ResultDto> resultDtoList = new ArrayList<>();
            while (resultSet.next()) {
                ResultDto resultDto = new ResultDto();
                resultDto.setTestName(resultSet.getString("name"));
                resultDto.setSubject(resultSet.getString("subject"));
                resultDto.setDifficult(resultSet.getInt("difficult"));
                resultDto.setDuration(resultSet.getInt("duration"));
                resultDto.setGrade(resultSet.getInt("grade"));
                resultDtoList.add(resultDto);
            }
            resultSet.close();
            return resultDtoList;
        } catch (SQLException e) {
            logger.warn("Can not get order result");
            throw new DataBaseException("Can not get order result" + e.getMessage(), e);

        }
    }

    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        String sql = "insert into result values(default, ?,?,?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, userId);
            pst.setLong(2, testId);
            pst.setInt(3, grade);
            return pst.executeUpdate();
        } catch (SQLException e) {
            logger.warn("Can not insert into result");
            throw new DataBaseException("Can not insert into result" + e.getMessage(), e);
        }
    }
}
