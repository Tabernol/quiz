package servises;

import dto.ResultDto;
import exeptions.DataBaseException;
import repo.ResultRepo;

import java.util.List;

public class ResultService {
    ResultRepo resultRepo = new ResultRepo();

    public void addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        resultRepo.addResult(userId, testId, grade);
    }

    public List<ResultDto> getResultByUser(Long userId) throws DataBaseException {
       return resultRepo.resultDtoList(userId);
    }
}
