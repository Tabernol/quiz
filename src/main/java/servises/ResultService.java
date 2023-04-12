package servises;

import dto.AnswerDto;
import dto.ResultDto;
import exeptions.DataBaseException;
import util.query.MyQuery;
import util.query.QueryBuilderForResult;
import util.query.QueryCreator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public interface ResultService {

    int addResult(Long userId, Long testId, Integer grade) throws DataBaseException;

    boolean getResultByQuestion(Long questionId, String[] userAnswer) throws DataBaseException;

    Integer getCountResultByUser(Long user_id, String sub) throws DataBaseException;

    Integer getCountPagesResult(Long userId, Integer row, String sub) throws DataBaseException;

    List<ResultDto> getPageResultList(Long userId, String sub, String order, Integer rows, Integer page) throws DataBaseException;

    List<ResultDto> getAllResultByUserId(Long userId) throws DataBaseException;

    Integer getGrade(List<Boolean> userAnswer, Integer countQuestion);

    List<String> getDistinctSubject(Long userId) throws DataBaseException;
}
