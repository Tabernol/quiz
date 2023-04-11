package repo.impl;

import connection.MyDataSource;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Question;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repo.QuestionRepo;

/**
 * Class repository has relationship with table Question in MySQL
 *
 * @author MaxKrasnopolskyi
 */
@Slf4j
public class QuestionRepoImpl implements QuestionRepo {

    /**
     * @param testId is identification Test(quiz) in database,
     *               it is foreign key for few Question in table 'question' in database
     * @return List of Question by testId
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<Question> getAllById(Long testId) throws DataBaseException {
        String sql = "select * from epam_project_testing.question where test_id = ?";
        List<Question> questions = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, testId.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Question question = new Question();
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                question.setUrlImage(resultSet.getString("url"));
                questions.add(question);
            }
            resultSet.close();
            return questions;
        } catch (SQLException e) {
            log.warn("Can't get order question by test id");
            throw new DataBaseException("Can't get order question by test id" + e.getMessage(), e);

        }
    }

    /**
     * Method return Question by id
     *
     * @param id is identification of Question in table 'question',
     * @return Question from database by input id
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Question get(Long id) throws DataBaseException {
        String sql = "select * from epam_project_testing.question where id = ?";
        Question question = new Question();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            ResultSet resultSet = pst.executeQuery();
            if (resultSet.next()) {
                question.setId(resultSet.getLong("id"));
                question.setTestId(resultSet.getLong("test_id"));
                question.setText(resultSet.getString("q_text"));
                question.setUrlImage(resultSet.getString("url"));
            }
            resultSet.close();
            return question;
        } catch (SQLException e) {
            log.warn("Can't get question by id");
            throw new DataBaseException("Can't get question by id" + e.getMessage(), e);
        }
    }

    /**
     * method update text of Question in database in table 'question'
     *
     * @param newText new text of question
     * @param id      is identification of Question in table 'question'
     * @return 1 if question will be updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int updateQuestion(String newText, Long id) throws DataBaseException {
        String sql = "update epam_project_testing.question set q_text = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, newText);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can' update question by id");
            throw new DataBaseException("Can' update question by id" + e.getMessage(), e);
        }
    }

    /**
     * method delete Question from database by id
     *
     * @param id is identification of Question in table 'question'
     * @return 1 if question will be deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(Long id) throws DataBaseException {
        String sql = "delete from epam_project_testing.question where id = ?";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can' delete question by id");
            throw new DataBaseException("Can' delete question by id" + e.getMessage(), e);
        }
    }

    /**
     * method create new Question and input in database
     *
     * @param question - a new question is passed from the service layer
     * @return 1 if question will be deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int create(Question question) throws DataBaseException {
        String sql = "insert into epam_project_testing.question (id, test_id, q_text) values(default, ?, ?)";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setLong(1, question.getTestId());
            pst.setString(2, question.getText());
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can not create question by id");
            throw new DataBaseException("Can not create question by id" + e.getMessage(), e);
        }
    }

    /**
     * method update URL column in database
     *
     * @param url is url-address of image
     * @param id  is identification of Question in table 'question'
     * @return 1 if question will be updated
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int updateImageQuestion(String url, Long id) throws DataBaseException {
        String sql = "update epam_project_testing.question set url = ? where id = ? ";
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(sql)) {
            pst.setString(1, url);
            pst.setLong(2, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can' update image question by id");
            throw new DataBaseException("Can' update image question by id" + e.getMessage(), e);
        }
    }
}
