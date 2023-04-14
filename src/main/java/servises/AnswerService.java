package servises;

import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;

import java.util.Set;

public interface AnswerService extends BaseService<AnswerDto>{
    Set<AnswerDto> getAnswers(Long questionId) throws DataBaseException;
}
