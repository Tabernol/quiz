package servises.impl;

import dto.AnswerDto;
import dto.ResultDto;
import exeptions.DataBaseException;
import lombok.extern.slf4j.Slf4j;
import repo.ResultRepo;
import repo.impl.AnswerRepoImpl;
import repo.impl.ResultRepoImpl;
import servises.AnswerService;
import servises.ResultService;
import util.query.MyQuery;
import util.query.QueryBuilderForResult;
import util.query.QueryCreator;
import validator.DataValidator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class is responsibility for checking answer and supplying grade after test(quiz),
 * and calls to insert completed result to database
 */
@Slf4j
public class ResultServiceImpl implements ResultService {
    private final ResultRepo resultRepo;
    private final AnswerService answerService;


    public ResultServiceImpl(ResultRepo resultRepo, AnswerService answerService) {
        this.answerService = answerService;
        this.resultRepo = resultRepo;
    }

    /**
     * This method calls to ResultRepo to add new new result
     *
     * @param userId is unique number of user in database
     * @param testId is unique number of test in database
     * @param grade  is result of test
     * @return
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        log.info("SERVICE RESULT add result to database");
        return resultRepo.addResult(userId, testId, grade);
    }

    /**
     * This method helps to check the result of the user's answer to a single question.
     *
     * @param userAnswer is the array which includes id answer and user flag "on" or "of"
     *                   for example {"12", "on", "13" "off", "23" "off"}
     * @return List of user`s Answers
     */

    private Set<AnswerDto> convertUserResult(String[] userAnswer) {
        Set<AnswerDto> userResult = new HashSet<>();
        AnswerDto answerDto = null;
        for (int i = 0; i < userAnswer.length; i++) {
            if (userAnswer[i].equals("on")) {
                answerDto.setResult(true);
                userResult.add(answerDto);
            } else if (userAnswer[i].equals("off")) {
                answerDto.setResult(false);
                userResult.add(answerDto);
            } else {
                answerDto = new AnswerDto();
                answerDto.setId(Long.parseLong(userAnswer[i]));
            }
        }
        return userResult;
    }

    /**
     * This method compares the user's answers to the question, and the answers stored in the database
     *
     * @param questionId is unique number Question is database
     * @param userAnswer is the array which includes id answer and user flag "on" or "of"
     *                   for example {"12", "on", "13" "off", "23" "off"}
     * @return true if all of user's answers match the saved answers, false otherwise
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public boolean getResultByQuestion(Long questionId, String[] userAnswer) throws DataBaseException {
        boolean result = true;
        if (userAnswer == null) {
            return result;
        } else {
            Set<AnswerDto> checkingAnswer = convertUserResult(userAnswer);
            Set<AnswerDto> trueAnswers = answerService.getAnswers(questionId);
            log.info("+++++++++++++++++++++++++++++++++++++++++++");
            log.info(checkingAnswer.toString());
            log.info(trueAnswers.toString());
            for (AnswerDto answerUser : checkingAnswer) {
                boolean contain = trueAnswers.contains(answerUser);
                if (contain == false) {
                    result = false;
                    break;
                }
            }
        }
        log.info("SERVICE RESULT get result by question " + questionId);
        return result;
    }

    /**
     * This method checks what parameter 'sub'
     * and calls to overload method in ResultRepo.class
     *
     * @param user_id is unique number of user in database
     * @param sub     is a unique name subject in the 'test' table in the database or all of them
     * @return The number of completed test by user with choose filter(sub)
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCountResultByUser(Long user_id, String sub) throws DataBaseException {
        log.info("SERVICE RESULT getting count of result with selected filter");
        if (sub.equals("all")) {
            return resultRepo.getCountResultByUser(user_id);
        } else {
            return resultRepo.getCountResultByUserAndSubject(user_id, sub);
        }
    }

    /**
     * @param userId is unique number of user in database
     * @param row    the number of rows on one page
     * @param sub    is a unique name subject in the 'test' table in the database or all of them
     * @return the count pages with results user
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public Integer getCountPagesResult(Long userId, Integer row, String sub) throws DataBaseException {
        log.info("SERVICE RESULT get count of pages for selected filter");
        Integer passedTest = getCountResultByUser(userId, sub);
        return passedTest % row == 0 ? passedTest / row : (passedTest / row) + 1;

    }

    /**
     * This method return List of result on each page
     *
     * @param userId is unique number of user in database
     * @param sub    is a unique name subject in the 'test' table in the database or all of them
     * @param order  is order of sample from table'test'
     * @param rows   is number of how many results include in List
     * @param page   is page of result
     * @return List of passed test(quiz) by user
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<ResultDto> getPageResultList(Long userId, String sub, String order, Integer rows, Integer page)
            throws DataBaseException {
        QueryCreator queryCreator = new QueryBuilderForResult();
        String query = queryCreator.getSQL(MyQuery.builder()
                .filter(String.valueOf(userId))
                .and(sub)
                .orderBy(order)
                .limit(rows)
                .page(page)
                .build());

        log.info("SERVICE RESULT get list of result with selected filter");
        return resultRepo.getPageResultList(query);
    }

    /**
     * This method calls to ResultRepo.class for return all completed test by user
     *
     * @param userId is unique number of user in database
     * @return List of passed test(quiz) by user
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<ResultDto> getAllResultByUserId(Long userId) throws DataBaseException {
        log.info("SERVICE RESULT get result by user {}", userId);
        return resultRepo.getAllResult(userId);
    }

    /**
     * This method counts percent of true answers
     *
     * @param userAnswer    is List of user`s answer
     * @param countQuestion is count of question in test(quiz)
     * @return grade from 0 to 100
     */
    @Override
    public Integer getGrade(List<Boolean> userAnswer, Integer countQuestion) {
        long count = userAnswer.stream().filter(bool -> bool.equals(true)).count();
        log.info("SERVICE RESULT get grade of completed quiz");
        return Math.toIntExact(count * 100 / countQuestion);
    }

    /**
     * This method calls to ResultRepo.class for received List unique name subject
     *
     * @param userId is unique number of user in database
     * @return list of unique name subject in the 'test' table in the database
     * @throws DataBaseException is wrapper of SQLException
     */
    @Override
    public List<String> getDistinctSubject(Long userId) throws DataBaseException {
        log.info("SERVICE RESULT get distinct subject of completed quiz");
        return resultRepo.getDistinctSubject(userId);
    }
}
