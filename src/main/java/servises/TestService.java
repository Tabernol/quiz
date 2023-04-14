package servises;

import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Test;

import java.util.List;

public interface TestService extends BaseService<TestDto> {
    List<String> getDistinctSubjects() throws DataBaseException;

    List<Test> getPageTestList(String subject, String order, Integer rows, Integer page, String role)
            throws DataBaseException;

    int countPages(String subject, Integer rowsOnPage) throws DataBaseException;

    int addPointPopularity(Long idTest) throws DataBaseException;

    int changeStatus(Long testId) throws DataBaseException;
}
