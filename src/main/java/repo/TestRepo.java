package repo;

import exeptions.DataBaseException;
import models.Test;

import java.util.List;

public interface TestRepo extends BaseRepo<Test> {
    List<String> getDistinctSubject() throws DataBaseException;

    int updateInfoTest(Test test) throws DataBaseException;

    Integer getCount(String subject) throws DataBaseException;

    Integer getCount() throws DataBaseException;

    List<Test> nextPage(String query) throws DataBaseException;

    Test get(Long id) throws DataBaseException;

    int addPopularity(Long idTest) throws DataBaseException;

    boolean isNameExist(String name) throws DataBaseException;

    int delete(Long id) throws DataBaseException;

    int create(Test test) throws DataBaseException;

    int changeStatus(Long id, Test.Status status) throws DataBaseException;
}
