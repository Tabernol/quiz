package servises;

import dto.ResultDto;
import exeptions.DataBaseException;
import repo.ResultRepo;

import java.util.List;

public class ResultService {
    ResultRepo resultRepo;

    public ResultService(ResultRepo resultRepo) {
        this.resultRepo = resultRepo;
    }

    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        return resultRepo.addResult(userId, testId, grade);
    }

    public List<ResultDto> getResultByUser(Long userId) throws DataBaseException {
        return resultRepo.resultDtoList(userId);
    }
}
