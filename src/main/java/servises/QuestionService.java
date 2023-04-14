package servises;

import dto.QuestionDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;

import java.util.List;

public interface QuestionService extends BaseService<QuestionDto> {
    List<QuestionDto> getAllById(Long id) throws DataBaseException;

    int updateImage(String url, Long id) throws DataBaseException, ValidateException;
}
