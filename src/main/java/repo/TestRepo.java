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

    String CREATE_TEST = "insert into epam_project_testing.test " +
            "(id, name, subject, difficult, duration, status) values(default, ?, ?, ?, ?, default)";

    String UPDATE_TEST = "update epam_project_testing.test set name = ?, subject = ?," +
            " difficult = ?, duration = ? where id = ? ";
    String DELETE_TEST = "delete from epam_project_testing.test where id = ?";
    String GET_TEST = "select * from epam_project_testing.test where id = ?";

    String GET_DISTINCT_SUBJECT = "select distinct subject from epam_project_testing.test";

    String COUNT_BY_SUBJECT = "select count(subject) from epam_project_testing.test where subject like ?";

    String COUNT_OF_SUBJECTS = "select count(subject) from epam_project_testing.test";

    String ADD_POINT_POPULARITY = "update epam_project_testing.test set popularity = popularity + 1 where id = ?";

    String IS_TEST_EXIST = "select * from epam_project_testing.test where name like ?";

    String CHANGE_STATUS_TEST = "update epam_project_testing.test set status = ? where id = ? ";

    String SUBJECT = "subject";
}
