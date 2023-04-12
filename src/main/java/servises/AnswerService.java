package servises;

import dto.AnswerDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Answer;

import java.util.HashSet;
import java.util.Set;

public interface AnswerService {
    Set<AnswerDto> getAnswers(Long questionId) throws DataBaseException;
    long createAnswer(AnswerDto answerDto) throws DataBaseException, ValidateException;
    int deleteAnswer(Long id) throws DataBaseException;
}
