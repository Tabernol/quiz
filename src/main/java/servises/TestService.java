package servises;

import dto.TestDto;
import exeptions.DataBaseException;
import exeptions.ValidateException;
import models.Test;
import util.query.MyQuery;
import util.query.QueryBuilderForTest;
import util.query.QueryCreator;

import java.util.List;

public interface TestService {
    long createTest(TestDto testDto)
            throws ValidateException, DataBaseException;

    List<String> getDistinctSubjects() throws DataBaseException;

    List<Test> getPageTestList(String subject, String order, Integer rows, Integer page, String role)
            throws DataBaseException;

    int delete(Long id) throws DataBaseException;

    TestDto get(Long id) throws DataBaseException;

    int update(TestDto testDto) throws DataBaseException, ValidateException;

    int countPages(String subject, Integer rowsOnPage) throws DataBaseException;

    int addPointPopularity(Long idTest) throws DataBaseException;

    int changeStatus(Long testId) throws DataBaseException;
}
