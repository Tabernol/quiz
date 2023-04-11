package repo.impl;

import connection.MyDataSource;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import models.Answer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import repo.AnswerRepo;

/**
 * Class repository has relationship with table Answer in MySQL
 *
 * @author MaxKrasnopolskyi
 */
@Slf4j
public class AnswerRepoImpl implements AnswerRepo {

    /**
     * method return List of answer for this questionId
     *
     * @param questionId is identification question in database,
     *                   and it is foreign key for few answers in table 'answer' in database
     * @return List of Answer by question
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<Answer> getAnswersByQuestionId(Long questionId) throws DataBaseException {
        List<Answer> answers = new ArrayList<>();
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(GET_ANSWERS_BY_QUESTION_ID)) {
            pst.setString(1, questionId.toString());
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()) {
                Answer answer = new Answer();
                answer.setId(resultSet.getLong("id"));
                answer.setQuestionId(resultSet.getLong("question_id"));
                answer.setText(resultSet.getString("a_text"));
                answer.setResult(resultSet.getBoolean("result"));
                answers.add(answer);
            }
            resultSet.close();
            return answers;
        } catch (SQLException e) {
            log.warn("Can't get orders answer by question id problem");
            throw new DataBaseException("Can't get orders by question id problem" + e.getMessage(), e);
        }
    }

    /**
     * method create new Answer for has chosen question by questionId
     *
     * @param answer is a new answer is passed from the service layer
     * @return 1 if Answer has created
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int create(Answer answer) throws DataBaseException {
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(CREATE_ANSWER)) {
            pst.setLong(1, answer.getQuestionId());
            pst.setString(2, answer.getText());
            pst.setBoolean(3, answer.isResult());
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can't insert answer problem ");
            throw new DataBaseException("Can't insert answer problem " + e.getMessage(), e);
        }
    }

    /**
     * method delete answer from database
     *
     * @param id is identification Answer in database
     * @return 1 if Answer has deleted
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int delete(Long id) throws DataBaseException {
        try (Connection con = MyDataSource.getConnection();
             PreparedStatement pst = con.prepareStatement(DELETE_ANSWER)) {
            pst.setLong(1, id);
            return pst.executeUpdate();
        } catch (SQLException e) {
            log.warn("Can't delete answer problem");
            throw new DataBaseException("Can't delete answer problem" + e.getMessage(), e);
        }
    }

    @Override
    public int update(Answer answer) throws DataBaseException {
        return 0;
    }

    @Override
    public Answer get(Long id) throws DataBaseException {
        return null;
    }

}
