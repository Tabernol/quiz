package servises;

import dao.impl.ResultDao;
import dto.ResultDto;
import models.Test;
import repo.ResultRepo;

import java.util.List;

public class ResultService {
    ResultDao resultDao = new ResultDao();
    ResultRepo resultRepo = new ResultRepo();

    public void addResult(Long userId, Long testId, Integer grade){
        resultDao.addResult(userId, testId, grade);
    }

    public List<ResultDto> getResultByUser(Long userId){
       return resultRepo.resultDtoList(userId);
    }
}
