package repo;

import exeptions.DataBaseException;
import models.Test;

import java.util.List;

public interface TestRepo extends BaseRepo<Test> {
    List<String> getDistinctSubject() throws DataBaseException;

    Integer getCount(String subject) throws DataBaseException;

    Integer getCount() throws DataBaseException;

    List<Test> nextPage(String query) throws DataBaseException;

    int addPopularity(Long idTest) throws DataBaseException;

    boolean isNameExist(String name) throws DataBaseException;

    int changeStatus(Long id, Test.Status status) throws DataBaseException;

}
