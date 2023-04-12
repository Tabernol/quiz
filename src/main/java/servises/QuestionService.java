package servises;

import dto.QuestionDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Question;

import java.util.ArrayList;
import java.util.List;

public interface QuestionService {
    List<QuestionDto> getAllById(Long id) throws DataBaseException;

    long addQuestion(QuestionDto questionDto) throws DataBaseException, ValidateException;

    int deleteQuestion(Long id) throws DataBaseException;

    QuestionDto get(Long id) throws DataBaseException;

    int update(QuestionDto questionDto) throws DataBaseException, ValidateException;

    int updateImage(String url, Long id) throws DataBaseException, ValidateException;
}
