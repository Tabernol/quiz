package repo;

import dao.connection.MyDataSource;
import dto.ResultDto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ResultRepo {
    public List<ResultDto> resultDtoList(Long userId) {
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
            throw new RuntimeException(e);
        }
    }
}
