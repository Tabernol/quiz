package repo;

import exeptions.DataBaseException;
import models.Question;

import java.util.List;

public interface QuestionRepo extends BaseRepo<Question> {
    List<Question> getAllById(Long testId) throws DataBaseException;

    int updateImageQuestion(String url, Long id) throws DataBaseException;

    String DELETE_QUESTION = "delete from epam_project_testing.question where id = ?";

    String CREATE_QUESTION = "insert into epam_project_testing.question (id, test_id, q_text) values(default, ?, ?)";

    String UPDATE_QUESTION = "update epam_project_testing.question set q_text = ? where id = ? ";

    String GET_QUESTION = "select * from epam_project_testing.question where id = ?";

    String GET_ALL_BY_ID = "select * from epam_project_testing.question where test_id = ?";

    String UPDATE_URL = "update epam_project_testing.question set url = ? where id = ? ";
}
