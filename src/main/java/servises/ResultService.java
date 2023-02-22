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

public class ResultService {
    private ResultRepo resultRepo;

    private AnswerService answerService;

    public ResultService(ResultRepo resultRepo) {
        this.answerService = new AnswerService(new AnswerRepo(), new ValidatorService(new DataValidator()));
        this.resultRepo = resultRepo;
    }

    public int addResult(Long userId, Long testId, Integer grade) throws DataBaseException {
        return resultRepo.addResult(userId, testId, grade);
    }

//    public List<ResultDto> getResultByUser(Long userId) throws DataBaseException {
//        return resultRepo.resultDtoList(userId);
//    }

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
                //  if(checkingAnswer.get(i).getId()==trueAnswers.get(i).getId())
                boolean contain = trueAnswers.contains(checkingAnswer.get(i));
                if (contain == false) {
                    result = false;
                    break;
                }
            }
        }
        return result;
    }

    public Integer getCountResultByUser(Long user_id, String sub) throws DataBaseException {
        if (sub.equals("all")) {
            return resultRepo.getCountResultByUser(user_id);
        } else {
            return resultRepo.getCountResultByUserAndSubject(user_id, sub);
        }

    }

    public Integer getCountPagesResult(Long userId, Integer row, String sub) throws DataBaseException {
        Integer passedTest = getCountResultByUser(userId, sub);
        return passedTest % row == 0 ? passedTest / row : (passedTest / row) + 1;

    }


    public List<ResultDto> getPageResultList(Long idUser, String sub, String order, Integer limit, Integer offSet)
            throws DataBaseException {
        QueryBuilderForResult queryBuilder = new QueryBuilderForResult();
        queryBuilder.setFilter(idUser.toString());
        queryBuilder.setOrderBy(order);
        queryBuilder.setLimit(limit);
        queryBuilder.setOffSet(offSet);
        queryBuilder.setAndSubject(sub);

        String query = queryBuilder.getQuery();
        System.out.println("QUERY = " + query);
        return resultRepo.getPageResultList(query);
    }

    public List<ResultDto> getAllResultByUserId(Long userId) throws DataBaseException {
        return resultRepo.getAllResult(userId);
    }


    public Integer getGrade(List<Boolean> userAnswer, Integer countQuestion) {
        long count = userAnswer.stream().filter(bool -> bool.equals(true)).count();
        return Math.toIntExact(count * 100 / countQuestion);
    }
}
