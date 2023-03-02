package servises;

import dto.ResultDto;
import exeptions.DataBaseException;
import models.Answer;
import repo.AnswerRepo;
import repo.ResultRepo;
import util.query.QueryBuilderForResult;
import validator.DataValidator;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is responsibility for checking answer and supplying grade after test(quiz),
 * and calls to insert completed result to database
 */
public class ResultService {
    private ResultRepo resultRepo;
    private AnswerService answerService;


    public ResultService(ResultRepo resultRepo) {
        this.answerService = new AnswerService(new AnswerRepo(), new ValidatorService(new DataValidator()));
        this.resultRepo = resultRepo;
    }

    /**
     * This method calls to ResultRepo to add new new result
     * @param userId is unique number of user in database
     * @param testId is unique number of test in database
     * @param grade is result of test
     * @return
     * @throws DataBaseException
     */
    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        return resultRepo.addResult(userId, testId, grade);
    }

    /**
     * This method helps to check the result of the user's answer to a single question.
     * @param userAnswer is the array which includes id answer and user flag "on" or "of"
     * for example {"12", "on", "13" "off", "23" "off"}
     * @return List of user`s Answers
     */
    private List<Answer> convertUserResult(String[] userAnswer) {
        List<Answer> userResult = new ArrayList<>();
        Answer answer = null;
        for (int i = 0; i < userAnswer.length; i++) {
            if (userAnswer[i].equals("on")) {
                answer.setResult(true);
                userResult.add(answer);
            } else if (userAnswer[i].equals("off")) {
                answer.setResult(false);
                userResult.add(answer);
            } else {
                answer = new Answer();
                answer.setId(Long.parseLong(userAnswer[i]));
            }
        }
        return userResult;
    }

    /**
     * This method compares the user's answers to the question, and the answers stored in the database
     * @param questionId is unique number Question is database
     * @param userAnswer is the array which includes id answer and user flag "on" or "of"
     * for example {"12", "on", "13" "off", "23" "off"}
     * @return true if all of user's answers match the saved answers, false otherwise
     * @throws DataBaseException
     */
    public boolean getResultByQuestion(Long questionId, String[] userAnswer) throws DataBaseException {
        boolean result = true;
        if (userAnswer == null) {
            return result;
        } else {
            List<Answer> checkingAnswer = convertUserResult(userAnswer);
            List<Answer> trueAnswers = answerService.getAnswers(questionId);
            System.out.println("user answers  " + checkingAnswer);
            System.out.println("true answers " + trueAnswers);

            for (int i = 0; i < checkingAnswer.size(); i++) {
                boolean contain = trueAnswers.contains(checkingAnswer.get(i));
                if (contain == false) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    /**
     * This method checks what parameter 'sub'
     * and calls to overload method in ResultRepo.class
     * @param user_id is unique number of user in database
     * @param sub is a unique name subject in the 'test' table in the database or all of them
     * @return The number of completed test by user with choose filter(sub)
     * @throws DataBaseException
     */
    public Integer getCountResultByUser(Long user_id, String sub) throws DataBaseException {
        if (sub.equals("all")) {
            return resultRepo.getCountResultByUser(user_id);
        } else {
            return resultRepo.getCountResultByUserAndSubject(user_id, sub);
        }

    }

    /**
     * @param userId is unique number of user in database
     * @param row the number of rows on one page
     * @param sub is a unique name subject in the 'test' table in the database or all of them
     * @return the count pages with results user
     * @throws DataBaseException
     */
    public Integer getCountPagesResult(Long userId, Integer row, String sub) throws DataBaseException {
        Integer passedTest = getCountResultByUser(userId, sub);
        return passedTest % row == 0 ? passedTest / row : (passedTest / row) + 1;

    }

    /**
     * This method return List of result on each page
     * @param userId is unique number of user in database
     * @param sub is a unique name subject in the 'test' table in the database or all of them
     * @param order is order of sample from table'test'
     * @param limit is number of how many results include in List
     * @param offSet is number of how many rows should not include in List
     * @return List of passed test(quiz) by user
     * @throws DataBaseException
     */
    public List<ResultDto> getPageResultList(Long userId, String sub, String order, Integer limit, Integer offSet)
            throws DataBaseException {
        QueryBuilderForResult queryBuilder = new QueryBuilderForResult();
        queryBuilder.setFilter(userId.toString());
        queryBuilder.setOrderBy(order);
        queryBuilder.setLimit(limit);
        queryBuilder.setOffSet(offSet);
        queryBuilder.setAndSubject(sub);

        String query = queryBuilder.getQuery();
        System.out.println("QUERY = " + query);
        return resultRepo.getPageResultList(query);
    }

    /**
     * This method calls to ResultRepo.class for return all completed test by user
     * @param userId is unique number of user in database
     * @return List of passed test(quiz) by user
     * @throws DataBaseException
     */
    public List<ResultDto> getAllResultByUserId(Long userId) throws DataBaseException {
        return resultRepo.getAllResult(userId);
    }

    /**
     * This method counts percent of true answers
     * @param userAnswer is List of user`s answer
     * @param countQuestion is count of question in test(quiz)
     * @return grade from 0 to 100
     */
    public Integer getGrade(List<Boolean> userAnswer, Integer countQuestion) {
        long count = userAnswer.stream().filter(bool -> bool.equals(true)).count();
        return Math.toIntExact(count * 100 / countQuestion);
    }

    /**
     * This method calls to ResultRepo.class for received List unique name subject
     * @param userId is unique number of user in database
     * @return list of unique name subject in the 'test' table in the database
     * @throws DataBaseException
     */
    public List<String> getDistinctSubject(Long userId) throws DataBaseException {
        return resultRepo.getDistinctSubject(userId);
    }
}
